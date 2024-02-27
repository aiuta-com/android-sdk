package com.aiuta.fashionsdk.tryon.core.domain.slice.ping

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.generatePingControllerFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal.PingOperationSliceImpl

internal val Fashion.pingOperationSliceFactory: PingOperationSlice
    get() =
        PingOperationSliceImpl(
            controllerFactory = { this.generatePingControllerFactory },
        )
