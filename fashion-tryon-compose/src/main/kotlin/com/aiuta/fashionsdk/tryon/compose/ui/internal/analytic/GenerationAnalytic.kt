package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.internal.analytic.model.ErrorEvent
import com.aiuta.fashionsdk.internal.analytic.model.ErrorEvent.ErrorType
import com.aiuta.fashionsdk.internal.analytic.model.SuccessEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.core.domain.models.meta.AiutaTryOnMetadata
import kotlin.time.Duration
import kotlin.time.DurationUnit

internal fun FashionTryOnController.sendSuccessTryOnEvent(
    metadata: AiutaTryOnMetadata,
    downloadDuration: Duration,
) {
    val finishTryOnTime = AiutaTryOnMetadata.markNow()
    val totalDuration = finishTryOnTime - metadata.startSecondsTimestamp

    // Notify internal
    analytic.sendEvent(
        event =
            SuccessEvent(
                productId = activeSKUItem.value.skuId,
                uploadDuration = metadata.uploadDurationSeconds.toDouble(DurationUnit.SECONDS),
                tryOnDuration = metadata.tryOnDurationSeconds.toDouble(DurationUnit.SECONDS),
                downloadDuration = downloadDuration.toDouble(DurationUnit.SECONDS),
                totalDuration = totalDuration.toDouble(DurationUnit.SECONDS),
            ),
    )

    // Notify public
    analytic.sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_FINISHED,
                pageId = AiutaAnalyticPageId.LOADING,
                productId = activeSKUItem.value.skuId,
            ),
    )
}

internal fun FashionTryOnController.sendErrorDownloadResultEvent() {
    // Notify internal
    analytic.sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
                errorMessage = "Failed to download result",
                pageId = AiutaAnalyticPageId.LOADING,
                productId = activeSKUItem.value.skuId,
            ),
    )

    // Notify public
    analytic.sendEvent(
        event =
            ErrorEvent(
                productId = activeSKUItem.value.skuId,
                error = ErrorType.DOWNLOAD_RESULT_FAILED,
            ),
    )
}
