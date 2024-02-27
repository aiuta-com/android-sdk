package com.aiuta.fashionsdk.tryon.core.domain.compressor

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.tryon.core.domain.compressor.internal.ImageCompressorImpl

internal val Fashion.imageCompressorFactory: ImageCompressor
    get() = ImageCompressorImpl(context = this.application)
