package com.aiuta.fashionsdk.tryon.core.domain.models.image

import androidx.compose.ui.graphics.ImageBitmap

public expect class PlatformImage {
    public val byteArray: ByteArray

    public val imageBitmap: ImageBitmap
}
