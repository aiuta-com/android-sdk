package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.code.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.AiutaCodeEntity

@Dao
internal interface AiutaCodeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(aiutaCodeEntity: AiutaCodeEntity)

    @Query("SELECT * from aiuta_codes LIMIT 1")
    suspend fun getCodes(): List<AiutaCodeEntity>

    @Query("DELETE from aiuta_codes")
    suspend fun removeAll()
}

@Transaction
internal suspend fun AiutaCodeDao.replaceAll(aiutaCodeEntity: AiutaCodeEntity) {
    removeAll()
    insert(aiutaCodeEntity)
}
