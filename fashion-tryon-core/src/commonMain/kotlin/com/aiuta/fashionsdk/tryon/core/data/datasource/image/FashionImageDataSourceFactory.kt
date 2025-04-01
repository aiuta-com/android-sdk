package com.aiuta.fashionsdk.tryon.core.data.datasource.image

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.defaultNetworkClient
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.internal.FashionImageRemoteDataSource

internal val Aiuta.imageDataSourceFactory: FashionImageDataSource
    get() =
        FashionImageRemoteDataSource(
            networkClient =
            defaultNetworkClient(
                aiuta = this,
            ),
        )
