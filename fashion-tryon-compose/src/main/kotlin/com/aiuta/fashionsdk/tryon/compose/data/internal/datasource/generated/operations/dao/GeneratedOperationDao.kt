package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationWithImages

internal interface GeneratedOperationDao {
    // Combined operation
    @Transaction
    @Query("SELECT * FROM generated_operation")
    fun getGeneratedOperationWithImages(): List<GeneratedOperationWithImages>

    // Raw operation
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOperation(newOperation: GeneratedOperationEntity)

    @Query("SELECT * FROM generated_operation WHERE id == :operationId")
    fun getOperation(operationId: Long)
}
