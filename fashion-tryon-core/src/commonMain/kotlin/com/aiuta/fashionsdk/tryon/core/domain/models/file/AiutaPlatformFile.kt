package com.aiuta.fashionsdk.tryon.core.domain.models.file

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.core.domain.models.compressor.AiutaCompressionConfig

public expect class AiutaPlatformFile

public expect suspend fun AiutaPlatformFile.readByteArray(platformContext: AiutaPlatformContext): ByteArray

public expect suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig = AiutaCompressionConfig.DEFAULT,
): ByteArray
