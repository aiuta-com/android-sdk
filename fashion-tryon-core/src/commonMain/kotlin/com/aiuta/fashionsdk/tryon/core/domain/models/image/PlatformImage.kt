package com.aiuta.fashionsdk.tryon.core.domain.models.image

import androidx.compose.ui.graphics.ImageBitmap

public expect class PlatformImage {
    val byteArray: ByteArray

    val imageBitmap: ImageBitmap
}
