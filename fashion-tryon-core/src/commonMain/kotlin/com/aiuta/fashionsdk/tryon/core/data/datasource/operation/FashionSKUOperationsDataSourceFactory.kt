package com.aiuta.fashionsdk.tryon.core.data.datasource.operation

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.internal.FashionProductOperationsRemoteDataSource

internal val Aiuta.productOperationsDataSourceFactory: FashionProductOperationsDataSource
    get() =
        FashionProductOperationsRemoteDataSource(
            networkClient =
            defaultNetworkClient(
                aiuta = this,
            ),
        )
