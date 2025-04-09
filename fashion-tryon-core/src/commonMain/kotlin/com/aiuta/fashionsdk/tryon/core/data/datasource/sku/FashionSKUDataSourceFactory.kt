package com.aiuta.fashionsdk.tryon.core.data.datasource.sku

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.internal.FashionProductRemoteDataSource

internal val Aiuta.productDataSourceFactory: FashionProductDataSource
    get() =
        FashionProductRemoteDataSource(
            networkClient =
            defaultNetworkClient(
                aiuta = this,
            ),
        )
