package com.aiuta.fashionsdk.tryon.core.domain.slice.ping

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.generatePingControllerFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal.PingOperationSliceImpl

internal val Aiuta.pingOperationSliceFactory: PingOperationSlice
    get() =
        PingOperationSliceImpl(
            controllerFactory = { this.generatePingControllerFactory },
        )
