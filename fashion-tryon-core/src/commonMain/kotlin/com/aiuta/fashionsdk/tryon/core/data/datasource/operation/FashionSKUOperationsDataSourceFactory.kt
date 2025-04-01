package com.aiuta.fashionsdk.tryon.core.data.datasource.operation

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.internal.FashionSKUOperationsRemoteDataSource

internal val Aiuta.skuOperationsDataSourceFactory: FashionSKUOperationsDataSource
    get() =
        FashionSKUOperationsRemoteDataSource(
            networkClient =
            defaultNetworkClient(
                aiuta = this,
            ),
        )
