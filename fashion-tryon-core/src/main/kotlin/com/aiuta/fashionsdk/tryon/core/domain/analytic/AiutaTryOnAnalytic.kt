package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer

private const val MILLISECONDS_IN_SECOND = 1000F

internal fun InternalAiutaAnalytic.sendStartTryOnEvent(container: SKUGenerationContainer) {
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_STARTED,
                pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                productId = container.skuId,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendFinishTryOnEvent(
    container: SKUGenerationContainer,
    loadingTimeMillis: Long,
) {
    // TODO?
    val loadingTimeSeconds = loadingTimeMillis / MILLISECONDS_IN_SECOND

    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_FINISHED,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnErrorEvent(
    container: SKUGenerationContainer,
    errorMessage: String? = null,
) {
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
                errorMessage = errorMessage,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnAbortedErrorEvent(
    container: SKUGenerationContainer,
    errorMessage: String? = null,
) {
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_ABORTED,
                errorMessage = errorMessage,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnPhotoUploadedEvent(container: SKUGenerationContainer) {
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.PHOTO_UPLOADED,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
}
