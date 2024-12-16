package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception

public class TryOnGenerationException(
    public val type: TryOnExceptionType,
    override val message: String? = null,
) : RuntimeException()

public enum class TryOnExceptionType {
    PREPARE_PHOTO_FAILED,
    UPLOAD_PHOTO_FAILED,
    START_OPERATION_FAILED,
    OPERATION_FAILED,
    OPERATION_ABORTED_FAILED,
    OPERATION_TIMEOUT_FAILED,
    DOWNLOAD_RESULT_FAILED,
}

public fun Exception.isTryOnGenerationAbortedException(): Boolean {
    return this is TryOnGenerationException && this.type == TryOnExceptionType.OPERATION_ABORTED_FAILED
}
