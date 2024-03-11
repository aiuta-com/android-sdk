package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal.GeneratePingControllerImpl

internal val Aiuta.generatePingControllerFactory: GeneratePingController
    get() =
        GeneratePingControllerImpl(
            skuOperationsDataSource = this.skuOperationsDataSourceFactory,
        )
