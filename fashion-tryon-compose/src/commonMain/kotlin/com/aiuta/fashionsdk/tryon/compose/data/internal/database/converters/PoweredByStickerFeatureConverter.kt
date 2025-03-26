package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.PoweredByStickerFeature

@ProvidedTypeConverter
internal class PoweredByStickerFeatureConverter : BaseSerializedConverter() {
    @TypeConverter
    fun restoreAiutaStylistBanner(rawString: String?): PoweredByStickerFeature? {
        return restore(rawString)
    }

    @TypeConverter
    fun saveAiutaStylistBanner(config: PoweredByStickerFeature): String? {
        return save(config)
    }
}
