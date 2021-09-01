package com.xdys.library.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hjq.toast.ToastUtils
import com.xdys.library.config.Constant
import com.xdys.library.event.LiveDataBus
import com.xdys.library.event.ReLoginEvent
import com.xdys.library.kit.request.RequestLauncherWrapper

abstract class BaseFragment<B : ViewBinding> : Fragment() {

    private var hasLoaded = false
    private var _binding: B? = null

    /**
     * 只能在OnCreateView()和OnDestroyView()之间调用
     */
    protected val binding: B get() = _binding!!

    @Deprecated("不需要封装来着...直接初始化就完事了", ReplaceWith("Unit"))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRequest()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = createBinding(inflater, container)
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        if (!hasLoaded) {
            initData()
            hasLoaded = true
        }
    }

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
    protected open fun initRequest() = Unit

    /**
     * 请求权限
     */
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

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): B

    protected fun isUserLogin(): Boolean {
        return if (Constant.token.isNullOrBlank()) {
            LiveDataBus.post(ReLoginEvent())
            false
        } else true
    }
}