package com.aiuta.fashionsdk.tryon.core.data.datasource.sku

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.internal.FashionSKURemoteDataSource

internal val Fashion.skuDataSourceFactory: FashionSKUDataSource
    get() =
        FashionSKURemoteDataSource(
            networkClient =
                defaultNetworkClient(
                    fashion = this,
                ),
        )
