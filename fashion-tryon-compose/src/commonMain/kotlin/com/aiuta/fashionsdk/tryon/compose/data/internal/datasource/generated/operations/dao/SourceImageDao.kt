package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.SourceImageEntity

@Dao
internal interface SourceImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(sourceImage: SourceImageEntity): Long

    @Query("SELECT * FROM source_images WHERE rowid = :sourceImageRowId LIMIT 1")
    suspend fun getImage(sourceImageRowId: Long): SourceImageEntity

    @Query("DELETE FROM source_images WHERE operationId = :operationId")
    suspend fun deleteImages(operationId: String)

    @Query("DELETE from source_images")
    suspend fun removeAll()
}
