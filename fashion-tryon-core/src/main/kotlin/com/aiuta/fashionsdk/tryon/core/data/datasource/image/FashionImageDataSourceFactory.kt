package com.aiuta.fashionsdk.tryon.core.data.datasource.image

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.internal.FashionImageRemoteDataSource

internal val Fashion.imageDataSourceFactory: FashionImageDataSource
    get() =
        FashionImageRemoteDataSource(
            networkClient =
                defaultNetworkClient(
                    fashion = this,
                ),
        )
