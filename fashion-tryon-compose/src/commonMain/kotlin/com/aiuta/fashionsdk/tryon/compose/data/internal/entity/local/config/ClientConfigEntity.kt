package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.config

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.FeedbackFeatureConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.FitDisclaimerFeatureConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.PoweredByStickerFeatureConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.TryOnModelsCategoriesConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfig
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.ClientConfiguration
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.PoweredByStickerFeature
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory

@Entity(tableName = "client_config")
internal class ClientConfigEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val etag: String? = null,
    @TypeConverters(PoweredByStickerFeatureConverter::class)
    val poweredByStickerFeature: PoweredByStickerFeature? = null,
    @TypeConverters(FeedbackFeatureConverter::class)
    val feedbackFeature: FeedbackFeature? = null,
    @TypeConverters(FitDisclaimerFeatureConverter::class)
    val fitDisclaimerFeature: FitDisclaimerFeature? = null,
    @TypeConverters(TryOnModelsCategoriesConverter::class)
    val predefinedTryOnModels: List<TryOnModelsCategory>? = null,
)

internal fun ClientConfigEntity.toDTO() =
    ClientConfig(
        etag = etag,
        clientConfiguration =
            ClientConfiguration(
                poweredByStickerFeature = poweredByStickerFeature,
                feedbackFeature = feedbackFeature,
                fitDisclaimerFeature = fitDisclaimerFeature,
                predefinedTryOnModels = predefinedTryOnModels,
            ),
    )

internal fun ClientConfig.toEntity() =
    ClientConfigEntity(
        etag = etag,
        poweredByStickerFeature = clientConfiguration.poweredByStickerFeature,
        feedbackFeature = clientConfiguration.feedbackFeature,
        fitDisclaimerFeature = clientConfiguration.fitDisclaimerFeature,
        predefinedTryOnModels = clientConfiguration.predefinedTryOnModels,
    )
