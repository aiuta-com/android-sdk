package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal.GeneratePingControllerImpl

internal val Fashion.generatePingControllerFactory: GeneratePingController
    get() =
        GeneratePingControllerImpl(
            skuOperationsDataSource = this.skuOperationsDataSourceFactory,
        )
