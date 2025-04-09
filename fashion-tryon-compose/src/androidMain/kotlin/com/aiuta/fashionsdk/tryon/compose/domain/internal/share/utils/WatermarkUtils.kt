package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.core.graphics.scale
import kotlin.math.min

private const val PORTRAIT_WIDTH_COEF = 0.5f
private const val LANDSCAPE_WIDTH_COEF = 0.25f

private const val PORTRAIT_HEIGHT_COEF = 0.15f
private const val LANDSCAPE_HEIGHT_COEF = 0.1f

private const val PADDING_COEF = 0.05f

internal fun Context.addWatermark(
    source: Bitmap,
    watermark: ImageBitmap,
): Bitmap {
    val canvas = Canvas(source)
    val proceedWatermark = getScaledWatermarkBitmap(
        source = source,
        watermark = watermark,
    )

    val padding = min(source.width, source.height) * PADDING_COEF

    val watermarkX = source.width - proceedWatermark.width - padding
    val watermarkY = source.height - proceedWatermark.height - padding
    val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG or Paint.FILTER_BITMAP_FLAG)
    canvas.drawBitmap(proceedWatermark, watermarkX, watermarkY, paint)
    proceedWatermark.recycle()

    return source
}

private fun Context.getScaledWatermarkBitmap(
    source: Bitmap,
    watermark: ImageBitmap,
): Bitmap {
    val isPortrait = source.height > source.width

    // Decide coefficient for scale
    val widthCoef = if (isPortrait) PORTRAIT_WIDTH_COEF else LANDSCAPE_WIDTH_COEF
    val heightCoef = if (isPortrait) PORTRAIT_HEIGHT_COEF else LANDSCAPE_HEIGHT_COEF

    val sourceWidthWithCoef = source.width * widthCoef
    val sourceHeightWithCoef = source.height * heightCoef

    // Decide should scale or not, if watermark is bigger than possible width
    // No more than 1, because we don't want to make watermark
    val original = watermark.asAndroidBitmap()
    val scaleWidthCoef = (sourceWidthWithCoef / original.width).coerceAtMost(1f)
    val scaleHeightCoef = (sourceHeightWithCoef / original.width).coerceAtMost(1f)
    val scaleCoef = min(scaleWidthCoef, scaleHeightCoef)

    val watermarkWidth = (original.width * scaleCoef).toInt()
    val watermarkHeight = (original.height * scaleCoef).toInt()

    return original.scale(watermarkWidth, watermarkHeight)
}
