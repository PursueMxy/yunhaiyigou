package com.xdys.library.widget.code

import android.text.method.PasswordTransformationMethod
import android.view.View

class NumberPasswordTransformationMethod : PasswordTransformationMethod() {

    override fun getTransformation(source: CharSequence?, view: View?): CharSequence {
        return PasswordCharSequence(source)
    }

    private class PasswordCharSequence(private val source: CharSequence?) : CharSequence {
        override val length: Int
            get() = source?.length ?: 0

        override fun get(index: Int): Char = '•'

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return source?.subSequence(startIndex, endIndex) ?: ""
        }
    }
}