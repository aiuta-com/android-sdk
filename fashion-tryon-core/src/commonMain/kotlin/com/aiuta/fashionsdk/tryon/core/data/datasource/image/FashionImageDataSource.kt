package com.aiuta.fashionsdk.tryon.core.data.datasource.image

import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage

internal interface FashionImageDataSource {
    suspend fun createUploadedImage(
        fileName: String,
        fileByteArray: ByteArray,
    ): UploadedImage

    suspend fun getUploadedImages(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<UploadedImage>

    suspend fun getUploadedImage(imageId: String): UploadedImage

    suspend fun deleteUploadedImage(imageId: String): Boolean
}
