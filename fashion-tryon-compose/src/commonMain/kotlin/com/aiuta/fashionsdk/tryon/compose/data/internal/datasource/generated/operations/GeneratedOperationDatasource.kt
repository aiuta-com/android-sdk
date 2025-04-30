package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations

import androidx.paging.PagingSource
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.GeneratedOperationDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.SourceImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.SourceImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImages
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GeneratedOperationDatasource(
    private val generatedOperationDao: GeneratedOperationDao,
    private val sourceImageDao: SourceImageDao,
) {
    // Combined operation
    fun pagingGeneratedOperationWithImagesSource(): PagingSource<Int, GeneratedOperationWithImages> = generatedOperationDao.pagingGeneratedOperationWithImagesSource()

    suspend fun getFirstGeneratedOperationWithImages(): GeneratedOperationWithImages? = withContext(Dispatchers.IO) {
        generatedOperationDao.getFirstGeneratedOperationWithImages()
    }

    // Raw operation
    suspend fun createOperation(): GeneratedOperationEntity = withContext(Dispatchers.IO) {
        val newOperation = GeneratedOperationEntity()
        generatedOperationDao.insertOperation(newOperation)

        newOperation
    }

    suspend fun deleteOperation(operationId: String) = withContext(Dispatchers.IO) {
        generatedOperationDao.deleteOperation(operationId)
        sourceImageDao.deleteImages(operationId)
    }

    fun countGeneratedOperation(): Flow<Int> = generatedOperationDao.countGeneratedOperation()

    // Source images
    suspend fun createImage(
        imageId: String,
        imageUrl: String,
        imageType: AiutaFileType,
        operationId: String,
    ): SourceImageEntity = withContext(Dispatchers.IO) {
        val newSourceImage = SourceImageEntity(
            id = imageId,
            operationId = operationId,
            imageUrl = imageUrl,
            imageType = imageType,
        )
        val rowId = sourceImageDao.insertImage(sourceImage = newSourceImage)

        sourceImageDao.getImage(sourceImageRowId = rowId)
    }

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): GeneratedOperationDatasource = GeneratedOperationDatasource(
            generatedOperationDao =
            AppDatabase.getInstance(
                platformContext,
            ).generatedOperationDao(),
            sourceImageDao = AppDatabase.getInstance(platformContext).sourceImageDao(),
        )
    }
}
