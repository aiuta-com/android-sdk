package com.aiuta.fashionsdk.tryon.core.domain.slice.upload

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.imageDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.internal.UploadImageSliceImpl

internal val Aiuta.uploadImageSliceFactory: UploadImageSlice
    get() =
        UploadImageSliceImpl(
            context = this.platformContext,
            analytic = this.internalAiutaAnalytic,
            imageDataSource = this.imageDataSourceFactory,
        )
