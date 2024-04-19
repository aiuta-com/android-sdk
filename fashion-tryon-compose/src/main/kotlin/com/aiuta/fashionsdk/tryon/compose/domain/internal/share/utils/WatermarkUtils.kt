package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import kotlin.math.min

private const val PORTRAIT_WIDTH_COEF = 0.5f
private const val LANDSCAPE_WIDTH_COEF = 0.25f

private const val PORTRAIT_HEIGHT_COEF = 0.15f
private const val LANDSCAPE_HEIGHT_COEF = 0.1f

private const val PADDING_COEF = 0.05f

internal fun Context.addWatermark(
    source: Bitmap,
    watermarkRes: Int,
): Bitmap {
    val canvas = Canvas(source)
    val watermark =
        getBitmapFromVectorDrawable(
            source = source,
            watermarkRes = watermarkRes,
        )

    val padding = min(source.width, source.height) * PADDING_COEF

    watermark?.let {
        val watermarkX = source.width - it.width - padding
        val watermarkY = source.height - it.height - padding
        val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)
        canvas.drawBitmap(it, watermarkX, watermarkY, paint)
        watermark.recycle()
    }
    return source
}

private fun Context.getBitmapFromVectorDrawable(
    source: Bitmap,
    watermarkRes: Int,
): Bitmap? {
    ContextCompat.getDrawable(this, watermarkRes)?.let { watermark ->
        val isPortrait = source.height > source.width

        // Decide coefficient for scale
        val widthCoef = if (isPortrait) PORTRAIT_WIDTH_COEF else LANDSCAPE_WIDTH_COEF
        val heightCoef = if (isPortrait) PORTRAIT_HEIGHT_COEF else LANDSCAPE_HEIGHT_COEF

        val sourceWidthWithCoef = source.width * widthCoef
        val sourceHeightWithCoef = source.height * heightCoef

        // Decide should scale or not, if watermark is bigger than possible width
        // No more than 1, because we don't want to make watermark
        val scaleWidthCoef =
            (sourceWidthWithCoef / watermark.intrinsicWidth).coerceAtMost(
                maximumValue = 1f,
            )
        val scaleHeightCoef =
            (sourceHeightWithCoef / watermark.intrinsicHeight).coerceAtMost(
                maximumValue = 1f,
            )

        val scaleCoef = min(scaleWidthCoef, scaleHeightCoef)

        val watermarkWidth = (watermark.intrinsicWidth * scaleCoef).toInt()
        val watermarkHeight = (watermark.intrinsicHeight * scaleCoef).toInt()

        watermark.setBounds(0, 0, watermarkWidth, watermarkHeight)
        val bitmap =
            Bitmap.createBitmap(
                watermarkWidth,
                watermarkHeight,
                Bitmap.Config.ARGB_8888,
            )
        val canvas = Canvas(bitmap)
        watermark.draw(canvas)
        return bitmap
    }
    return null
}
