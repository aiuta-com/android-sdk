package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.internal.analytic.model.ErrorEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException

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

internal fun InternalAiutaAnalytic.sendPublicTryOnErrorEvent(
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

internal fun InternalAiutaAnalytic.sendPublicTryOnAbortedErrorEvent(
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

internal fun InternalAiutaAnalytic.sendInternalErrorEvent(
    container: SKUGenerationContainer,
    type: AiutaTryOnExceptionType,
) {
    sendEvent(
        event =
        ErrorEvent(
            productId = container.skuId,
            error =
            when (type) {
                AiutaTryOnExceptionType.PREPARE_PHOTO_FAILED -> ErrorEvent.ErrorType.PREPARE_PHOTO_FAILED
                AiutaTryOnExceptionType.UPLOAD_PHOTO_FAILED -> ErrorEvent.ErrorType.UPLOAD_PHOTO_FAILED
                AiutaTryOnExceptionType.START_OPERATION_FAILED -> ErrorEvent.ErrorType.START_OPERATION_FAILED
                AiutaTryOnExceptionType.OPERATION_FAILED -> ErrorEvent.ErrorType.OPERATION_FAILED
                AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED -> ErrorEvent.ErrorType.OPERATION_ABORTED_FAILED
                AiutaTryOnExceptionType.OPERATION_TIMEOUT_FAILED -> ErrorEvent.ErrorType.OPERATION_TIMEOUT_FAILED
                AiutaTryOnExceptionType.DOWNLOAD_RESULT_FAILED -> ErrorEvent.ErrorType.DOWNLOAD_RESULT_FAILED
            },
        ),
    )
}

internal fun InternalAiutaAnalytic.sendErrorEvent(
    container: SKUGenerationContainer,
    exception: AiutaTryOnGenerationException,
) {
    // Send internal
    sendInternalErrorEvent(container = container, type = exception.type)

    // Send public
    when (exception.type) {
        AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED -> {
            sendPublicTryOnAbortedErrorEvent(
                container = container,
                errorMessage = exception.message,
            )
        }

        else -> {
            sendPublicTryOnErrorEvent(container = container, errorMessage = exception.message)
        }
    }
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
