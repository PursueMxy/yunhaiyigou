package com.xdys.library.kit.span

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.text.style.ReplacementSpan
import kotlin.math.roundToInt

class RoundBackgroundSpan(
    private val bgColor: Int = Color.BLACK,
    private val color: Int = Color.WHITE,
    private val radius: Float = 5f
) : ReplacementSpan() {

    override fun getSize(
        paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?
    ): Int = paint.measureText(text, start, end).roundToInt()

    override fun draw(
        canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float,
        top: Int, y: Int, bottom: Int, paint: Paint
    ) {
        val rect = RectF(
            x, top.toFloat(), x + measureText(paint, text.toString(), start, end),
            bottom.toFloat()
        )
        paint.color = bgColor
        canvas.drawRoundRect(rect, radius, radius, paint)
        paint.color = color
        val distance: Float =
            (paint.fontMetrics.bottom - paint.fontMetrics.top) / 2 - paint.fontMetrics.bottom
        val baseline: Float = rect.centerY() + distance
        canvas.drawText(text.toString(), start, end, x, baseline, paint)
    }

    private fun measureText(paint: Paint, text: CharSequence, start: Int, end: Int): Float {
        return paint.measureText(text, start, end)
    }
}