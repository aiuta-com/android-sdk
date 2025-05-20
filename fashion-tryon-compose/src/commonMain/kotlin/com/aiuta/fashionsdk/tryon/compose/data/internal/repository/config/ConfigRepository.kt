package com.aiuta.fashionsdk.tryon.compose.data.internal.repository.config

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.ConfigLocalDataSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.config.ConfigRemoteDataSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfig
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory
import com.aiuta.fashionsdk.tryon.compose.data.internal.repository.base.BaseRepository
import com.aiuta.fashionsdk.tryon.compose.domain.internal.time.TimeSaver
import kotlin.time.Duration.Companion.minutes

internal class ConfigRepository(
    private val timeSaver: TimeSaver,
    private val localDataSource: ConfigLocalDataSource,
    private val remoteDataSource: ConfigRemoteDataSource,
) : BaseRepository(REPOSITORY_KEY, timeSaver) {
    suspend fun loadConfig(forceUpdate: Boolean = false): ClientConfig = updatableLoad(
        delay = CONFIG_UPDATE_DURATION,
        forceUpdate = forceUpdate,
        remoteLoad = { forceLoad ->
            val etag = localDataSource.getEtag()
            remoteDataSource.getBackendConfig(etag.takeIf { !forceLoad })
        },
        localLoad = {
            localDataSource.getBackendConfig()
        },
        replaceLocalData = { config ->
            localDataSource.replaceConfig(config)
        },
    )

    suspend fun getTryOnModelsCategories(forceUpdate: Boolean = false): List<TryOnModelsCategory>? = loadConfig(forceUpdate).predefinedTryOnModels

    companion object {
        private const val REPOSITORY_KEY = "ConfigRepository"
        private val CONFIG_UPDATE_DURATION = 30.minutes

        fun getInstance(aiuta: Aiuta): ConfigRepository = ConfigRepository(
            timeSaver = TimeSaver.getInstance(aiuta.platformContext),
            localDataSource = ConfigLocalDataSource.getInstance(aiuta.platformContext),
            remoteDataSource = ConfigRemoteDataSource.getInstance(aiuta),
        )
    }
}
