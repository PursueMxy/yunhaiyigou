package com.xdys.library.extension

import android.text.Editable
import com.xdys.library.R
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

/**
 * 将金额保留小数点
 *
 * @param round 保留小数点的位数
 * @param needDecimal 如果入参是整数，是否需要补齐小数
 */
fun String.round(round: Int = 2, needDecimal: Boolean = false) = when {
    this.contains('.') || needDecimal -> {
        toBigDecimal().setScale(round, RoundingMode.HALF_UP).toString()
    }
    else -> this
}

/**
 * 将时间戳转换成指定数据格式的文字
 * @param pattern 指定的数据格式
 */
fun Long.toTime(pattern: String? = null) = SimpleDateFormat(
    pattern ?: context.getString(R.string.date_pattern), Locale.getDefault()
).format(Date(this))

/**
 * 将时间戳转换成指定数据格式的文字
 * @param sdf 指定的时间转换类
 */
fun Long.toTime(sdf: SimpleDateFormat? = null) = (sdf ?: SimpleDateFormat(
    context.getString(R.string.date_pattern), Locale.getDefault()
)).format(Date(this))

/**
 * EditText的金额格式化
 * @return 返回小数点的位置，-1代表没有小数点
 */
fun Editable.formatCurrency() {
    // 开头多个0,删除
    if (startsWith("00")) delete(0, 1)
    val dotIndex = indexOf('.')
    if (dotIndex == 0) {
        // 缺少0,添加
        insert(0, "0")
    } else if (dotIndex > 0 && dotIndex + 2 < lastIndex) {
        // 大于两位小数，删除
        delete(lastIndex, length)
    }
}