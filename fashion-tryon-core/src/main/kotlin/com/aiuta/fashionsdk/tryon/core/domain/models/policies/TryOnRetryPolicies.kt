package com.aiuta.fashionsdk.tryon.core.domain.models.policies

import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnExceptionType

public class TryOnRetryPolicies(
    // Counts
    public val photoUploadRetryCount: Int,
    public val operationStartRetryCount: Int,
    public val operationStatusRetryCount: Int,
    public val resultDownloadRetryCount: Int,
    // Delays
    public val retryDelay: Long,
)

internal val DefaultTryOnRetryPolicies by lazy {
    TryOnRetryPolicies(
        photoUploadRetryCount = 2,
        operationStartRetryCount = 0,
        operationStatusRetryCount = 2,
        resultDownloadRetryCount = 2,
        retryDelay = 1000L,
    )
}

internal fun TryOnExceptionType.toRetryCount(retryPolicies: TryOnRetryPolicies): Int {
    return when (this) {
        TryOnExceptionType.UPLOAD_PHOTO_FAILED -> retryPolicies.photoUploadRetryCount
        TryOnExceptionType.START_OPERATION_FAILED -> retryPolicies.operationStartRetryCount
        TryOnExceptionType.OPERATION_FAILED -> retryPolicies.operationStatusRetryCount
        TryOnExceptionType.DOWNLOAD_RESULT_FAILED -> retryPolicies.resultDownloadRetryCount
        else -> 0
    }
}
