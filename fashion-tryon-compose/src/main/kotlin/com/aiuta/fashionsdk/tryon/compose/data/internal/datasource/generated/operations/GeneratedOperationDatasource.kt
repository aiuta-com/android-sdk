package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations

import android.content.Context
import androidx.paging.PagingSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.GeneratedOperationDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.SourceImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.SourceImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GeneratedOperationDatasource(
    private val generatedOperationDao: GeneratedOperationDao,
    private val sourceImageDao: SourceImageDao,
) {
    // Combined operation
    fun pagingGeneratedOperationWithImagesSource(): PagingSource<Int, GeneratedOperationWithImages> {
        return generatedOperationDao.pagingGeneratedOperationWithImagesSource()
    }

    suspend fun getFirstGeneratedOperationWithImages(): GeneratedOperationWithImages? {
        return withContext(Dispatchers.IO) {
            generatedOperationDao.getFirstGeneratedOperationWithImages()
        }
    }

    // Raw operation
    suspend fun createOperation(): GeneratedOperationEntity {
        return withContext(Dispatchers.IO) {
            val newOperation = GeneratedOperationEntity()
            val rowId = generatedOperationDao.insertOperation(newOperation)

            generatedOperationDao.getOperation(operationRowId = rowId)
        }
    }

    suspend fun deleteOperation(operationId: Long) {
        return withContext(Dispatchers.IO) {
            generatedOperationDao.deleteOperation(operationId)
            sourceImageDao.deleteImages(operationId)
        }
    }

    fun countGeneratedOperation(): Flow<Int> {
        return generatedOperationDao.countGeneratedOperation()
    }

    // Source images
    suspend fun createImage(
        imageId: String,
        imageUrl: String,
        operationId: Long,
    ): SourceImageEntity {
        return withContext(Dispatchers.IO) {
            val newSourceImage =
                SourceImageEntity(
                    id = imageId,
                    operationId = operationId,
                    imageUrl = imageUrl,
                )
            val rowId = sourceImageDao.insertImage(sourceImage = newSourceImage)

            sourceImageDao.getImage(sourceImageRowId = rowId)
        }
    }

    companion object {
        fun getInstance(context: Context): GeneratedOperationDatasource {
            return GeneratedOperationDatasource(
                generatedOperationDao = AppDatabase.getInstance(context).generatedOperationDao(),
                sourceImageDao = AppDatabase.getInstance(context).sourceImageDao(),
            )
        }
    }
}
