package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images

import androidx.paging.PagingSource
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.dao.GeneratedImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GeneratedImageDatasource(
    private val generatedImageDao: GeneratedImageDao,
) {
    suspend fun insertAll(generatedImages: List<GeneratedImageEntity>) {
        withContext(Dispatchers.IO) {
            generatedImageDao.insertAll(
                generatedImages = generatedImages,
            )
        }
    }

    fun pagingSource(): PagingSource<Int, GeneratedImageEntity> {
        return generatedImageDao.pagingSource()
    }

    suspend fun remove(generatedImageIds: List<String>) {
        withContext(Dispatchers.IO) {
            if (generatedImageDao.count() == 0) return@withContext

            generatedImageDao.remove(
                generatedImageIds = generatedImageIds,
            )
        }
    }

    suspend fun removeAll() {
        withContext(Dispatchers.IO) {
            if (generatedImageDao.count() == 0) return@withContext

            generatedImageDao.removeAll()
        }
    }

    fun countFlow(): Flow<Int> {
        return generatedImageDao.countFlow()
    }

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): GeneratedImageDatasource {
            return GeneratedImageDatasource(
                generatedImageDao = AppDatabase.getInstance(platformContext).generatedImageDao(),
            )
        }
    }
}
