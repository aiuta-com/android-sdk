package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class GeneratedOperationFactory(
    private val generatedOperationInteractor: GeneratedOperationInteractor,
) {
    private val mutex = Mutex()
    private var operationId: Long? = null

    suspend fun getOperationId(): Long {
        return mutex.withLock {
            operationId ?: generatedOperationInteractor.createOperation().also {
                operationId = it
            }
        }
    }
}
