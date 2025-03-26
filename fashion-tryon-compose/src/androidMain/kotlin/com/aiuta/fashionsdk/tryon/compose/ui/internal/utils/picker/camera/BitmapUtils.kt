package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.camera

import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri

internal object BitmapUtils {
    fun getBitmapFromUri(
        uri: Uri,
        contentResolver: ContentResolver,
    ): android.graphics.Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            contentResolver.openInputStream(uri).use {
                BitmapFactory.decodeStream(inputStream)
            }
        } catch (e: Exception) {
            return null
        }
    }
}
