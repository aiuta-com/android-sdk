package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.PoweredByStickerFeatureConverter
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.ClientConfig
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.ClientConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.PoweredByStickerFeature

@Entity(tableName = "client_config")
internal class ClientConfigEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val etag: String? = null,
    @TypeConverters(PoweredByStickerFeatureConverter::class)
    val poweredByStickerFeature: PoweredByStickerFeature,
)

internal fun ClientConfigEntity.toDTO() =
    ClientConfig(
        etag = etag,
        clientConfiguration =
            ClientConfiguration(
                poweredByStickerFeature = poweredByStickerFeature,
            ),
    )

internal fun ClientConfig.toEntity() =
    ClientConfigEntity(
        etag = etag,
        poweredByStickerFeature = clientConfiguration.poweredByStickerFeature,
    )
