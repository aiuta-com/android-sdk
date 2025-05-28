package com.aiuta.fashionsdk.io

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.io.compression.AiutaCompressionConfig

/**
 * Platform-specific file implementation for handling file operations across different platforms.
 * This class provides a common interface for file operations while allowing platform-specific implementations.
 */
public expect class AiutaPlatformFile

/**
 * Reads the contents of the file as a byte array.
 *
 * @param platformContext The platform context to use for file operations
 * @return The contents of the file as a byte array
 */
public expect suspend fun AiutaPlatformFile.readByteArray(platformContext: AiutaPlatformContext): ByteArray

/**
 * Reads the contents of the file as a compressed byte array.
 *
 * @param platformContext The platform context to use for file operations
 * @param config The compression configuration to use (defaults to DEFAULT)
 * @return The compressed contents of the file as a byte array
 */
public expect suspend fun AiutaPlatformFile.readCompressedByteArray(
    platformContext: AiutaPlatformContext,
    config: AiutaCompressionConfig = AiutaCompressionConfig.DEFAULT,
): ByteArray
