package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImages
import kotlinx.coroutines.flow.Flow

@Dao
internal interface GeneratedOperationDao {
    // Combined operation
    @Transaction
    @Query("SELECT * FROM generated_operation ORDER BY id DESC")
    fun pagingGeneratedOperationWithImagesSource(): PagingSource<Int, GeneratedOperationWithImages>

    @Transaction
    @Query("SELECT * FROM generated_operation ORDER BY id DESC LIMIT 1")
    fun getLastGeneratedOperationWithImages(): GeneratedOperationWithImages

    // Raw operation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperation(newOperation: GeneratedOperationEntity): Long

    @Query("SELECT * FROM generated_operation WHERE rowid = :operationRowId LIMIT 1")
    fun getOperation(operationRowId: Long): GeneratedOperationEntity

    @Query("SELECT count(id) FROM generated_operation")
    fun countGeneratedOperation(): Flow<Int>

    @Query("DELETE FROM generated_operation WHERE id = :operationId")
    fun deleteOperation(operationId: Long)

    @Query("DELETE from generated_operation")
    suspend fun removeAll()
}
