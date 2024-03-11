package com.aiuta.fashionsdk.tryon.core.domain.compressor

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.domain.compressor.internal.ImageCompressorImpl

internal val Aiuta.imageCompressorFactory: ImageCompressor
    get() = ImageCompressorImpl(context = this.application)
