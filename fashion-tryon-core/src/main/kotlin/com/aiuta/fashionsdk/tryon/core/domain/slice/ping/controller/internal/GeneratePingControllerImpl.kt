package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal

import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.SKUOperationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.GeneratePingController
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal.utils.getGenerationDelaySequence
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class GeneratePingControllerImpl(
    private val skuOperationsDataSource: FashionSKUOperationsDataSource,
) : GeneratePingController {
    private var currentImageCount = 0
    private val mutex = Mutex()

    private val _pingGenerationStatus: MutableStateFlow<PingGenerationStatus> =
        MutableStateFlow(PingGenerationStatus.NothingGenerateStatus)
    override val pingGenerationStatus: StateFlow<PingGenerationStatus> = _pingGenerationStatus

    override suspend fun operationPing(operationId: String) {
        mutex.withLock {
            currentImageCount = 0
            _pingGenerationStatus.emit(PingGenerationStatus.StartPingGenerationStatus)

            try {
                ping(
                    operationId = operationId,
                    delaySequenceIterator = getGenerationDelaySequence().iterator(),
                )
            } catch (e: Exception) {
                _pingGenerationStatus.emit(
                    PingGenerationStatus.ErrorPingGenerationStatus(
                        errorMessage = e.message,
                        exception = e,
                    ),
                )
            }
        }
    }

    private suspend fun ping(
        operationId: String,
        delaySequenceIterator: Iterator<Long>,
    ) {
        val operation = skuOperationsDataSource.getSKUOperation(operationId)

        when (operation.status) {
            SKUOperationStatus.FAILED -> {
                _pingGenerationStatus.emit(
                    PingGenerationStatus.ErrorPingGenerationStatus(
                        errorMessage = operation.error,
                    ),
                )
                return
            }

            SKUOperationStatus.CREATED, SKUOperationStatus.IN_PROGRESS -> {
                if (currentImageCount != operation.generatedImages.size) {
                    _pingGenerationStatus.emit(
                        PingGenerationStatus.LoadingPingGenerationStatus(
                            imageUrls = operation.generatedImages.map { it.imageUrl },
                        ),
                    )
                }
            }

            SKUOperationStatus.SUCCESS -> {
                _pingGenerationStatus.emit(
                    PingGenerationStatus.SuccessPingGenerationStatus(
                        imageUrls = operation.generatedImages.map { it.imageUrl },
                    ),
                )
                return
            }
        }

        delay(delaySequenceIterator.next())

        ping(
            operationId = operationId,
            delaySequenceIterator = delaySequenceIterator,
        )
    }
}
