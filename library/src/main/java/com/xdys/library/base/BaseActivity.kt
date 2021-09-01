package com.xdys.library.base

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.hjq.toast.ToastUtils
import com.xdys.library.kit.request.RequestLauncherWrapper


abstract class BaseActivity<B : ViewBinding> : AppCompatActivity() {
    protected val TAG: String = this.javaClass.simpleName
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }
    /**
     * 设置状态栏颜色,默认为透明
     */
    @ColorInt
    var statusBarColor: Int = Color.BLACK
        set(value) {
            if (field != value) {
                field = value
                with(window) {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    statusBarColor = field
                }
            }
        }

    /**
     * 设置状态栏字体颜色(23及以上)
     */
    var statusBarTextDark: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) window.decorView.let {
                    if (field) it.systemUiVisibility = it.systemUiVisibility or
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    else it.systemUiVisibility = it.systemUiVisibility and
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
                }
            }
        }

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = createBinding()
        setContentView(binding.root)
        statusBarColor = Color.TRANSPARENT

        statusBarTextDark = true
        doBeforeInitUI()
        initUI(savedInstanceState)
        initData()
    }

    /**
     * 初始化视图之前的预操作
     */
    open fun doBeforeInitUI() {
        initRequest()
    }

    /**
     * 初始化视图
     *
     * @param savedInstanceState savedInstanceState
     */
    protected open fun initUI(savedInstanceState: Bundle?) = Unit

    /**
     * 初始化数据,在初始化视图之后调用
     */
    protected open fun initData() = Unit

    /**
     * 显示加载框
     */
    protected open fun showLoading(content: String) = Unit

    /**
     * 显示加载框
     */
    protected open fun showLoading(@StringRes resId: Int) = showLoading(getString(resId))

    /**
     * 隐藏加载框
     */
    protected open fun hideLoading() = Unit

    /**
     * 显示提示框
     */
    protected open fun showMessage(content: String) = ToastUtils.show(content)

    /**
     * 显示提示框
     */
    protected open fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    /**
     * 初始化权限申请
     */
    @Deprecated("不需要封装来着...直接初始化就完事了", ReplaceWith("Unit"))
    protected open fun initRequest() = Unit

//    /**
//     * 请求权限
//     */
    protected fun createPermissionLauncher(
        onGrant: () -> Unit, onDeny: () -> Unit
    ): RequestLauncherWrapper {
        val launcher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                var hasPermissionDeny = false
                for (entry in it) if (!entry.value) {
                    hasPermissionDeny = true
                    break
                }
                if (hasPermissionDeny) onDeny.invoke() else onGrant.invoke()
            }
        return RequestLauncherWrapper(launcher)
    }

    /**
     * 生成Binding
     */
    abstract fun createBinding(): B

    fun startActivity(clz: Class<*>) {
        startActivity(Intent(this, clz))

    }

    companion object {

        /**
         * 判断是否是快速点击
         */
        private var lastClickTime: Long = 0
        val isFastDoubleClick: Boolean
            get() {
                val time = System.currentTimeMillis()
                val timeD = time - lastClickTime
                if (timeD in 1..299) {
                    return true
                }
                lastClickTime = time
                return false

            }

    }
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        try {
            if (ev.action == MotionEvent.ACTION_DOWN) {
                if (isFastDoubleClick) {
                    return true
                }
            }
            return super.dispatchTouchEvent(ev)
        } catch (ex: IllegalArgumentException) {
            ex.printStackTrace()
        }
        return false
    }

}