package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.config.ClientConfigEntity

@Dao
internal interface ConfigDao {
    @Query("SELECT * from client_config LIMIT :limit")
    suspend fun getAll(limit: Int): List<ClientConfigEntity>

    @Query("SELECT etag from client_config LIMIT :limit")
    suspend fun getAllEtags(limit: Int): List<String>

    @Query("DELETE from client_config")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: ClientConfigEntity)
}

@Transaction
internal suspend fun ConfigDao.replaceAll(clientConfig: ClientConfigEntity) {
    deleteAll()
    insert(clientConfig)
}
