package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnErrorType
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.internal.analytic.model.ErrorEvent
import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException

internal fun InternalAiutaAnalytic.sendStartTryOnEvent(container: ProductGenerationContainer) {
    sendEvent(
        event =
        AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_STARTED,
            pageId = AiutaAnalyticPageId.IMAGE_PICKER,
            productId = container.productId,
        ),
    )
}

internal fun InternalAiutaAnalytic.sendPublicTryOnErrorEvent(
    container: ProductGenerationContainer,
    exception: Exception,
) {
    sendEvent(
        event =
        AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_ERROR,
            errorType = when (exception) {
                is AiutaTryOnGenerationException -> when (exception.type) {
                    AiutaTryOnExceptionType.PREPARE_PHOTO_FAILED -> AiutaAnalyticsTryOnErrorType.PREPARE_PHOTO_FAILED
                    AiutaTryOnExceptionType.UPLOAD_PHOTO_FAILED -> AiutaAnalyticsTryOnErrorType.UPLOAD_PHOTO_FAILED
                    AiutaTryOnExceptionType.START_OPERATION_FAILED -> AiutaAnalyticsTryOnErrorType.START_OPERATION_FAILED
                    AiutaTryOnExceptionType.OPERATION_FAILED -> AiutaAnalyticsTryOnErrorType.OPERATION_FAILED
                    AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED -> AiutaAnalyticsTryOnErrorType.OPERATION_ABORTED
                    AiutaTryOnExceptionType.OPERATION_TIMEOUT_FAILED -> AiutaAnalyticsTryOnErrorType.OPERATION_TIMEOUT
                    AiutaTryOnExceptionType.OPERATION_EMPTY_RESULTS_FAILED -> AiutaAnalyticsTryOnErrorType.OPERATION_EMPTY_RESULTS
                    AiutaTryOnExceptionType.DOWNLOAD_RESULT_FAILED -> AiutaAnalyticsTryOnErrorType.DOWNLOAD_RESULT_FAILED
                }
                is FashionIOException -> when (exception.errorCode) {
                    // Unauthorized
                    401 -> AiutaAnalyticsTryOnErrorType.AUTHORIZATION_FAILED
                    else -> AiutaAnalyticsTryOnErrorType.START_OPERATION_FAILED
                }
                else -> AiutaAnalyticsTryOnErrorType.INTERNAL_SDK_ERROR
            },
            errorMessage = exception.message,
            pageId = AiutaAnalyticPageId.LOADING,
            productId = container.productId,
        ),
    )
}

internal fun InternalAiutaAnalytic.sendPublicTryOnAbortedErrorEvent(
    container: ProductGenerationContainer,
    errorMessage: String? = null,
) {
    sendEvent(
        event = AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.TRY_ON_ABORTED,
            errorType = AiutaAnalyticsTryOnErrorType.OPERATION_ABORTED,
            errorMessage = errorMessage,
            pageId = AiutaAnalyticPageId.LOADING,
            productId = container.productId,
        ),
    )
}

internal fun InternalAiutaAnalytic.sendInternalErrorEvent(
    container: ProductGenerationContainer,
    type: AiutaTryOnExceptionType,
) {
    sendEvent(
        event =
        ErrorEvent(
            productId = container.productId,
            error = when (type) {
                AiutaTryOnExceptionType.PREPARE_PHOTO_FAILED -> ErrorEvent.ErrorType.PREPARE_PHOTO_FAILED
                AiutaTryOnExceptionType.UPLOAD_PHOTO_FAILED -> ErrorEvent.ErrorType.UPLOAD_PHOTO_FAILED
                AiutaTryOnExceptionType.START_OPERATION_FAILED -> ErrorEvent.ErrorType.START_OPERATION_FAILED
                AiutaTryOnExceptionType.OPERATION_FAILED -> ErrorEvent.ErrorType.OPERATION_FAILED
                AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED -> ErrorEvent.ErrorType.OPERATION_ABORTED_FAILED
                AiutaTryOnExceptionType.OPERATION_TIMEOUT_FAILED -> ErrorEvent.ErrorType.OPERATION_TIMEOUT_FAILED
                AiutaTryOnExceptionType.DOWNLOAD_RESULT_FAILED -> ErrorEvent.ErrorType.DOWNLOAD_RESULT_FAILED
                AiutaTryOnExceptionType.OPERATION_EMPTY_RESULTS_FAILED -> ErrorEvent.ErrorType.OPERATION_EMPTY_RESULTS_FAILED
            },
        ),
    )
}

internal fun InternalAiutaAnalytic.sendErrorEvent(
    container: ProductGenerationContainer,
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
            sendPublicTryOnErrorEvent(container = container, exception = exception)
        }
    }
}

internal fun InternalAiutaAnalytic.sendTryOnPhotoUploadedEvent(container: ProductGenerationContainer) {
    sendEvent(
        event =
        AiutaAnalyticsTryOnEvent(
            event = AiutaAnalyticsTryOnEventType.PHOTO_UPLOADED,
            pageId = AiutaAnalyticPageId.LOADING,
            productId = container.productId,
        ),
    )
}
