package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.dao.ConfigDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.dao.replaceAll
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.config.toDTO
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.config.toEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.ClientConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ConfigLocalDataSource(
    private val configDao: ConfigDao,
) {
    suspend fun getEtag(): String? {
        return withContext(Dispatchers.IO) {
            configDao.getAllEtags(limit = 1).firstOrNull()
        }
    }

    suspend fun getBackendConfig(): ClientConfig? {
        return withContext(Dispatchers.IO) {
            configDao.getAll(limit = 1).firstOrNull()?.toDTO()
        }
    }

    suspend fun replaceConfig(newConfig: ClientConfig) {
        return withContext(Dispatchers.IO) {
            configDao.replaceAll(newConfig.toEntity())
        }
    }

    companion object {
        fun getInstance(context: Context): ConfigLocalDataSource {
            return ConfigLocalDataSource(
                configDao = AppDatabase.getInstance(context).configDao(),
            )
        }
    }
}
