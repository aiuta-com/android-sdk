package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller

import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import kotlinx.coroutines.flow.StateFlow

/**
 * [GeneratePingController] is controller for ping
 * generate photo operations
 */
internal interface GeneratePingController {
    /**
     * Flow of [PingGenerationStatus] with current state of generation
     */
    public val pingGenerationStatus: StateFlow<PingGenerationStatus>

    /**
     * Start process of ping, roadmap should be like this:
     *
     *            NothingGenerateStatus
     *           /                 \   \
     *          /                   \   \
     * ErrorGenerationStatus         \   LoadingGenerationStatus
     *                                \           \
     *                               SuccessGenerationStatus
     */
    public suspend fun operationPing(operationId: String)
}
