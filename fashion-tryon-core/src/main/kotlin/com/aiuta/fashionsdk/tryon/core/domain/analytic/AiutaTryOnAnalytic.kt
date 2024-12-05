package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.internal.analytic.model.FinishTryOn
import com.aiuta.fashionsdk.internal.analytic.model.StartTryOn
import com.aiuta.fashionsdk.internal.analytic.model.TryOnError
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer

private const val MILLISECONDS_IN_SECOND = 1000L

internal fun InternalAiutaAnalytic.sendStartTryOnEvent(container: SKUGenerationContainer) {
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_STARTED,
                pageId = AiutaAnalyticPageId.IMAGE_PICKER,
                productId = container.skuId,
            ),
    )
    sendEvent(
        event =
            StartTryOn(
                skuId = container.skuId,
                skuCatalogName = container.skuCatalogName,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendFinishTryOnEvent(
    container: SKUGenerationContainer,
    loadingTimeMillis: Long,
) {
    val loadingTimeSeconds = loadingTimeMillis / MILLISECONDS_IN_SECOND

    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_FINISHED,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
    sendEvent(
        event =
            FinishTryOn(
                skuId = container.skuId,
                skuCatalogName = container.skuCatalogName,
                generationTime = loadingTimeSeconds.toString(),
            ),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnErrorEvent(
    type: TryOnError.Type,
    container: SKUGenerationContainer,
    errorMessage: String? = null,
) {
    sendEvent(event = TryOnError(type = type.value))
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
                errorMessage = "${type.value}: $errorMessage",
                pageId = AiutaAnalyticPageId.LOADING,
                productId = container.skuId,
            ),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnAbortedErrorEvent(
    type: TryOnError.Type,
    container: SKUGenerationContainer,
    errorMessage: String? = null,
) {
    sendEvent(event = TryOnError(type = type.value))
    sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_ABORTED,
                errorMessage = "${type.value}: $errorMessage",
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
