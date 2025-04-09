package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception

public class AiutaTryOnGenerationException(
    public val type: AiutaTryOnExceptionType,
    override val message: String? = "Failed to generate image, type of error - $type",
) : RuntimeException()

public enum class AiutaTryOnExceptionType {
    PREPARE_PHOTO_FAILED,
    UPLOAD_PHOTO_FAILED,
    START_OPERATION_FAILED,
    OPERATION_FAILED,
    OPERATION_ABORTED_FAILED,
    OPERATION_TIMEOUT_FAILED,
    DOWNLOAD_RESULT_FAILED,
}

public fun Exception.isTryOnGenerationAbortedException(): Boolean = this is AiutaTryOnGenerationException && this.type == AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED
