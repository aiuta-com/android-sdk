package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class GeneratedOperationFactory(
    private val generatedOperationInteractor: GeneratedOperationInteractor,
) {
    private val mutex = Mutex()
    private var operationId: String? = null

    suspend fun getOperationId(imageId: String): String = mutex.withLock {
        operationId ?: generatedOperationInteractor.createOperation(imageId).also {
            operationId = it
        }
    }
}
