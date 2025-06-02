package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticPageId
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnErrorType
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.analytics.events.AiutaAnalyticsTryOnEventType
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

    analytic.sendEvent(
        event = AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_FINISHED,
            pageId = AiutaAnalyticPageId.LOADING,
            productId = activeProductItem.value.id,
            uploadDuration = metadata.uploadDurationSeconds.toDouble(DurationUnit.SECONDS),
            tryOnDuration = metadata.tryOnDurationSeconds.toDouble(DurationUnit.SECONDS),
            downloadDuration = downloadDuration.toDouble(DurationUnit.SECONDS),
            totalDuration = totalDuration.toDouble(DurationUnit.SECONDS),
        ),
    )
}

internal fun FashionTryOnController.sendErrorDownloadResultEvent() {
    analytic.sendEvent(
        event = AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
            errorType = AiutaAnalyticsTryOnErrorType.DOWNLOAD_RESULT_FAILED,
            errorMessage = "Failed to download result",
            pageId = AiutaAnalyticPageId.LOADING,
            productId = activeProductItem.value.id,
        ),
    )
}
