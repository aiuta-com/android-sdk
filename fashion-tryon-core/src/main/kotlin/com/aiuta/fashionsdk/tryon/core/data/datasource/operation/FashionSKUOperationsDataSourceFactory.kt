package com.aiuta.fashionsdk.tryon.core.data.datasource.operation

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.internal.FashionSKUOperationsRemoteDataSource

internal val Fashion.skuOperationsDataSourceFactory: FashionSKUOperationsDataSource
    get() =
        FashionSKUOperationsRemoteDataSource(
            networkClient =
                defaultNetworkClient(
                    fashion = this,
                ),
        )
