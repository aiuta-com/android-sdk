package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal

import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.GeneratePingController
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.flow.StateFlow

internal class PingOperationSliceImpl(
    private val controllerFactory: () -> GeneratePingController,
) : PingOperationSlice {
    private val taskMap: ConcurrentHashMap<String, Task> = ConcurrentHashMap()

    override fun getPingGenerationStatusFlow(
        operationId: String,
    ): StateFlow<PingGenerationStatus>? {
        return taskMap[operationId]?.controller?.pingGenerationStatus
    }

    override suspend fun startOperationTypeListening(operationId: String) {
        require(taskMap[operationId] == null) {
            ERROR_OPERATION_EXIST
        }

        val controller = controllerFactory()
        taskMap[operationId] = Task(controller)

        controller.operationPing(operationId)
    }

    private class Task(
        val controller: GeneratePingController,
    )

    private companion object {
        const val ERROR_OPERATION_EXIST = """
            Generation operation with this id is already exist.
            Cancel this operation before submitting new
        """
    }
}
