package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal

import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.GeneratePingController
import java.util.concurrent.ConcurrentHashMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class PingOperationSliceImpl(
    private val controllerFactory: () -> GeneratePingController,
    coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : PingOperationSlice {
    private val innerScope = CoroutineScope(coroutineDispatcher + SupervisorJob())
    private val taskMap: ConcurrentHashMap<String, Task> = ConcurrentHashMap()

    override fun getPingGenerationStatusFlow(
        operationId: String,
    ): StateFlow<PingGenerationStatus>? {
        return taskMap[operationId]?.controller?.pingGenerationStatus
    }

    override fun startOperationTypeListening(operationId: String) {
        require(taskMap[operationId] == null) {
            ERROR_OPERATION_EXIST
        }

        val controller = controllerFactory()
        val job =
            innerScope.launch {
                controller.operationPing(operationId)
            }
        taskMap[operationId] = Task(job, controller)
    }

    override fun cancelOperation(operationId: String) {
        taskMap.remove(operationId)?.job?.cancel()
    }

    private data class Task(
        val job: Job,
        val controller: GeneratePingController,
    )

    private companion object {
        const val ERROR_OPERATION_EXIST = """
            Generation operation with this id is already exist.
            Cancel this operation before submitting new
        """
    }
}
