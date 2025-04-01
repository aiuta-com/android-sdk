package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.GeneratedImage
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.SKUOperationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnGenerationException
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal.utils.defaultGenerationDelaySequence
import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class PingOperationSliceImpl(
    private val skuOperationsDataSource: FashionSKUOperationsDataSource,
    private val delaySequenceFactory: () -> Sequence<Long> = { defaultGenerationDelaySequence() },
) : PingOperationSlice {
    private val mutex = Mutex()

    override suspend fun operationPing(operationId: String): List<GeneratedImage> = mutex.withLock {
        ping(
            operationId = operationId,
            delaySequenceIterator = delaySequenceFactory().iterator(),
        )
    }

    private suspend fun ping(
        operationId: String,
        delaySequenceIterator: Iterator<Long>,
    ): List<GeneratedImage> {
        val operation = skuOperationsDataSource.getSKUOperation(operationId)

        when (operation.status) {
            SKUOperationStatus.FAILED -> {
                throw AiutaTryOnGenerationException(
                    type = AiutaTryOnExceptionType.OPERATION_FAILED,
                    message = operation.error,
                )
            }

            SKUOperationStatus.ABORTED -> {
                throw AiutaTryOnGenerationException(
                    type = AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED,
                    message = operation.error,
                )
            }

            SKUOperationStatus.CREATED, SKUOperationStatus.IN_PROGRESS -> {
                // Just wait result
            }

            SKUOperationStatus.SUCCESS -> {
                return operation.generatedImages
            }
        }

        if (delaySequenceIterator.hasNext()) {
            delay(delaySequenceIterator.next())
        } else {
            // We reach time limit
            throw AiutaTryOnGenerationException(AiutaTryOnExceptionType.OPERATION_TIMEOUT_FAILED)
        }

        return ping(
            operationId = operationId,
            delaySequenceIterator = delaySequenceIterator,
        )
    }
}
