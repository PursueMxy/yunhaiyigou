package com.xdys.library.base

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonSyntaxException
import com.xdys.library.BuildConfig
import com.xdys.library.R
import com.xdys.library.config.Constant
import com.xdys.library.entity.InUploadEntity
import com.xdys.library.entity.UploadEntity
import com.xdys.library.event.DisposableLiveData
import com.xdys.library.event.LiveDataBus
import com.xdys.library.event.ReLoginEvent
import com.xdys.library.extension.context
import com.xdys.library.network.HttpClient
import com.xdys.library.network.HttpStatusException
import com.xdys.library.network.base.BaseApi
import com.xdys.library.network.base.BaseResult
import com.xdys.library.network.base.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import top.zibin.luban.Luban
import java.net.ConnectException
import java.net.SocketTimeoutException

open class BaseViewModel : ViewModel() {

    private var timer: CountDownTimer? = null

    /**
     * 倒计时观察者
     */
    val countdownLiveData by lazy { DisposableLiveData<Int>() }

    /**
     * 提示框观察者
     */
    val messageLiveData by lazy { DisposableLiveData<String?>() }

    /**
     * 加载框观察者
     * 参数: null    ->  隐藏
     *      ""      ->  不显示文字
     *      其他文字 ->  显示文字
     */
    val loadingLiveData by lazy { DisposableLiveData<String?>() }

    override fun onCleared() {
        timer?.cancel()
    }

    /**
     * 倒计时
     */
    fun countdown(second: Long) {
        viewModelScope.launch(Dispatchers.Main) {
            timer?.cancel()
            timer = object : CountDownTimer(second * 1000, 1000) {
                override fun onTick(p0: Long) {
                    countdownLiveData.postValue((p0 / 1000).toInt())
                }

                override fun onFinish() {
                    countdownLiveData.postValue(-1)
                }
            }
            timer?.start()
        }
    }

    /**
     * 取消倒计时
     */
    fun stopCountdown() {
        timer?.cancel()
    }

    /**
     * 请求数据，返回Response里面的data,null代表请求失败
     */
    suspend fun <T> fetchData(
        api: suspend () -> Result<T>,
        failure: ((Throwable) -> Unit)? = { handleThrowable(it) },
        complete: (() -> Unit)? = { loadingLiveData.postValue(null) }
    ): T? {
        return withContext(Dispatchers.IO) {
            try {
                val baseResult = api.invoke()
                if (baseResult.success) baseResult.data
                else {
                    failure?.invoke(HttpStatusException(baseResult.code, baseResult.msg))
                    null
                }
            } catch (e: Exception) {
                failure?.invoke(e)
                null
            } finally {
                complete?.invoke()
            }
        }
    }

    suspend fun <T> fetchMsgData(
        api: suspend () -> Result<T>,
        failure: ((Throwable) -> Unit)? = { handleThrowable(it) },
        complete: (() -> Unit)? = { }
    ): T? {
        return withContext(Dispatchers.IO) {
            try {
                val baseResult = api.invoke()
                if (baseResult.success) baseResult.data
                else {
                    messageLiveData.postValue(baseResult.msg)
                    failure?.invoke(HttpStatusException(baseResult.code, baseResult.msg))
                    null
                }
            } catch (e: Exception) {
                failure?.invoke(e)
                null
            } finally {
                complete?.invoke()
            }
        }
    }


    /**
     * 请求数据，返回Response里面的data,null代表请求失败
     */
    suspend fun fetchEmptyData(
        api: suspend () -> BaseResult,
        failure: ((Throwable) -> Unit)? = { handleThrowable(it) },
        complete: (() -> Unit)? = { loadingLiveData.postValue(null) }
    ): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val baseResult = api.invoke()
                if (baseResult.success) baseResult.code
                else {
                    failure?.invoke(HttpStatusException(baseResult.code, baseResult.msg))
                    null
                }
            } catch (e: Exception) {
                failure?.invoke(e)
                null
            } finally {
                complete?.invoke()
            }
        }
    }

    suspend fun fetchEmptyMsgData(
        api: suspend () -> BaseResult,
        failure: ((Throwable) -> Unit)? = { handleThrowable(it) },
        complete: (() -> Unit)? = { loadingLiveData.postValue(null) }
    ): Int? {
        return withContext(Dispatchers.IO) {
            try {
                val baseResult = api.invoke()
                if (baseResult.success) baseResult.code
                else {
                    messageLiveData.postValue(baseResult.msg)
                    failure?.invoke(HttpStatusException(baseResult.code, baseResult.msg))
                    null
                }
            } catch (e: Exception) {
                failure?.invoke(e)
                null
            } finally {
                complete?.invoke()
            }
        }
    }

    fun handleThrowable(throwable: Throwable) {
        if (BuildConfig.DEBUG) throwable.printStackTrace()
        when (throwable) {
            is HttpStatusException -> {
                if (throwable.code == 6002) LiveDataBus.post(ReLoginEvent())
                else messageLiveData.postValue(throwable.msg)
            }
            is JsonSyntaxException -> messageLiveData.postValue(context.getString(R.string.data_change_exception))
            is SocketTimeoutException -> {
                messageLiveData.postValue(context.getString(R.string.connect_timeout))
            }
            is ConnectException -> messageLiveData.postValue(context.getString(R.string.net_exception))
        }
    }


    /**
     * 上传图片
     */
    suspend fun uploadPicture(list: MutableList<UploadEntity>): InUploadEntity? {
        return withContext(Dispatchers.IO) {
            val builder = StringBuilder()
            val pathList = mutableListOf<String>()
            for (entity in list) {
                if (!entity.imgPath.isNullOrBlank()) {
                    pathList.add(entity.imgPath)
                } else if (entity.imgUrl.isNullOrBlank()) builder.append(entity.imgUrl)
                    .append(context.getString(R.string.comma))
            }
            val fileList = Luban.with(context).ignoreBy(500)
                .setTargetDir(Constant.Config.compressDir.path)
                .load(pathList).get()
            val bodyList = mutableListOf<MultipartBody.Part>()
            for (entity in fileList) {
                val body = entity.asRequestBody(
                    context.getString(R.string.image_mime).toMediaType()
                )
                bodyList.add(MultipartBody.Part.createFormData("file[]", entity.path, body))
            }
            fetchData(
                { HttpClient.create(BaseApi::class.java).uploadPicture(bodyList) }
            )
        }
    }


}