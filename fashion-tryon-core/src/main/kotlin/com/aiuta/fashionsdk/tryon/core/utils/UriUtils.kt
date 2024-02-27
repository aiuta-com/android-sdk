package com.aiuta.fashionsdk.tryon.core.utils

import android.content.Context
import android.net.Uri

internal fun readBytes(
    context: Context,
    uri: Uri,
): ByteArray? {
    return context.contentResolver
        .openInputStream(uri)
        ?.use { it.buffered().readBytes() }
}
