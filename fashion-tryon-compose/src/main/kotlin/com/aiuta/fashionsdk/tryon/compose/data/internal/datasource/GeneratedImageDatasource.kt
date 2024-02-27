package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource

import android.content.Context
import androidx.paging.PagingSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.dao.GeneratedImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity
import kotlinx.coroutines.Dispatchers
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

    suspend fun remove(generatedImageIds: List<Long>) {
        withContext(Dispatchers.IO) {
            generatedImageDao.remove(
                generatedImageIds = generatedImageIds,
            )
        }
    }

    companion object {
        fun getInstance(context: Context): GeneratedImageDatasource {
            return GeneratedImageDatasource(
                generatedImageDao = AppDatabase.getInstance(context).generatedImageDao(),
            )
        }
    }
}
