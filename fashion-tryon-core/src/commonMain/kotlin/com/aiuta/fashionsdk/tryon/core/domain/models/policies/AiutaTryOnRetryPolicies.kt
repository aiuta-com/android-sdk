package com.aiuta.fashionsdk.tryon.core.domain.models.policies

public class AiutaTryOnRetryPolicies(
    // Counts
    public val photoUploadRetryCount: Int,
    public val operationStartRetryCount: Int,
    public val operationStatusRetryCount: Int,
    public val resultDownloadRetryCount: Int,
    // Delays
    public val retryDelay: Long,
)

public val DefaultAiutaTryOnRetryPolicies: AiutaTryOnRetryPolicies by lazy {
    AiutaTryOnRetryPolicies(
        photoUploadRetryCount = 2,
        operationStartRetryCount = 0,
        operationStatusRetryCount = 2,
        resultDownloadRetryCount = 2,
        retryDelay = 1000L,
    )
}
