package com.xdys.library.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt


class SpanView(bgColor: Int = Color.BLACK, color: Int = Color.WHITE, r: Float = 5f) : ReplacementSpan() {

    private var backgroundColor = Color.BLACK
    private var textColor = Color.WHITE
    private var radius = 0.0f

    init {
        this.backgroundColor = bgColor
        this.textColor = color
        radius = r
    }

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        return paint.measureText(text, start, end).roundToInt()
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val rect = RectF(x, top.toFloat(), x + measureText(paint, text.toString(), start, end), bottom.toFloat())
        paint.color = backgroundColor
        canvas.drawRoundRect(rect, radius, radius, paint)
        paint.color = textColor
        val distance: Float = (paint.fontMetrics.bottom - paint.fontMetrics.top) / 2 - paint.fontMetrics.bottom
        val baseline: Float = rect.centerY() + distance
        canvas.drawText(text.toString(), start, end, x, baseline, paint)
    }

    private fun measureText(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
        return paint.measureText(text, start, end)
    }
}