package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
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
        StartTryOn(
            skuId = container.skuId,
            skuCatalogName = container.skuCatalogName,
        ),
    )
    sendEvent(event = AiutaAnalyticsTryOnEvent(event = AiutaAnalyticsTryOnEventType.TRY_ON_STARTED))
}

internal fun InternalAiutaAnalytic.sendFinishTryOnEvent(
    container: SKUGenerationContainer,
    loadingTimeMillis: Long,
) {
    val loadingTimeSeconds = loadingTimeMillis / MILLISECONDS_IN_SECOND

    sendEvent(
        event =
        FinishTryOn(
            skuId = container.skuId,
            skuCatalogName = container.skuCatalogName,
            generationTime = loadingTimeSeconds.toString(),
        ),
    )
    sendEvent(
        event = AiutaAnalyticsTryOnEvent(event = AiutaAnalyticsTryOnEventType.TRY_ON_FINISHED),
    )
}

internal fun InternalAiutaAnalytic.sendTryOnErrorEvent(
    type: TryOnError.Type,
    errorMessage: String? = null,
) {
    sendEvent(event = TryOnError(type = type.value))
    sendEvent(
        event = AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
            errorMessage = "${type.value}: $errorMessage"
        )
    )
}

internal fun InternalAiutaAnalytic.sendTryOnPhotoUploadedEvent() {
    sendEvent(event = AiutaAnalyticsTryOnEvent(event = AiutaAnalyticsTryOnEventType.PHOTO_UPLOADED))
}
