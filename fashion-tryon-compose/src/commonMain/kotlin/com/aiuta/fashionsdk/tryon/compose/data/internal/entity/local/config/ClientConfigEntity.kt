package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.TryOnModelsCategoriesConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfig
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfiguration
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory

@Entity(tableName = "client_config")
internal class ClientConfigEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val etag: String? = null,
    @TypeConverters(TryOnModelsCategoriesConverter::class)
    val predefinedTryOnModels: List<TryOnModelsCategory>? = null,
)

internal fun ClientConfigEntity.toDTO() = ClientConfig(
    etag = etag,
    clientConfiguration = ClientConfiguration(
        predefinedTryOnModels = predefinedTryOnModels,
    ),
)

internal fun ClientConfig.toEntity() = ClientConfigEntity(
    etag = etag,
    predefinedTryOnModels = clientConfiguration.predefinedTryOnModels,
)
