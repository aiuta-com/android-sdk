package com.aiuta.fashionsdk.tryon.core.domain.models.policies

import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType

internal fun AiutaTryOnExceptionType.toRetryCount(retryPolicies: AiutaTryOnRetryPolicies): Int = when (this) {
    AiutaTryOnExceptionType.UPLOAD_PHOTO_FAILED -> retryPolicies.photoUploadRetryCount
    AiutaTryOnExceptionType.START_OPERATION_FAILED -> retryPolicies.operationStartRetryCount
    AiutaTryOnExceptionType.OPERATION_FAILED -> retryPolicies.operationStatusRetryCount
    AiutaTryOnExceptionType.DOWNLOAD_RESULT_FAILED -> retryPolicies.resultDownloadRetryCount
    else -> 0
}
