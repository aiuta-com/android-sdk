package com.aiuta.fashionsdk.io

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.io.compression.AiutaCompressionConfig

public expect class AiutaPlatformFile

public expect suspend fun AiutaPlatformFile.readByteArray(platformContext: AiutaPlatformContext): ByteArray

public expect suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig = AiutaCompressionConfig.DEFAULT,
): ByteArray
