package com.aiuta.fashionsdk.tryon.core.data.datasource.sku

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.internal.FashionSKURemoteDataSource

internal val Aiuta.skuDataSourceFactory: FashionSKUDataSource
    get() =
        FashionSKURemoteDataSource(
            networkClient =
            defaultNetworkClient(
                aiuta = this,
            ),
        )
