package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity

@Dao
internal interface GeneratedImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(generatedImages: List<GeneratedImageEntity>)

    @Query("SELECT * FROM generated_images")
    fun pagingSource(): PagingSource<Int, GeneratedImageEntity>

    @Query("DELETE FROM generated_images WHERE id in (:generatedImageIds)")
    suspend fun remove(generatedImageIds: List<Long>)
}
