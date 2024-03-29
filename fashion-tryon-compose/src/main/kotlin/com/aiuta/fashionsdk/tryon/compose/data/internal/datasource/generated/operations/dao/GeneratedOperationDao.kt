package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationWithImages

@Dao
internal interface GeneratedOperationDao {
    // Combined operation
    @Transaction
    @Query("SELECT * FROM generated_operation")
    fun pagingGeneratedOperationWithImagesSource(): PagingSource<Int, GeneratedOperationWithImages>

    // Raw operation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperation(newOperation: GeneratedOperationEntity): Long

    @Query("SELECT * FROM generated_operation WHERE rowid == :operationRowId LIMIT 1")
    fun getOperation(operationRowId: Long): GeneratedOperationEntity
}
