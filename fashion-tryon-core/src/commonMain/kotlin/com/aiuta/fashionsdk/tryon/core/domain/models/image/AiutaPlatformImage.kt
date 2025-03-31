package com.aiuta.fashionsdk.tryon.core.domain.models.image

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.ImageBitmap

@Immutable
public expect class AiutaPlatformImage {
    public val byteArray: ByteArray

    public val imageBitmap: ImageBitmap
}
