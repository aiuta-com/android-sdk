package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FeedbackFeature

@ProvidedTypeConverter
internal class FeedbackFeatureConverter : BaseSerializedConverter() {
    @TypeConverter
    fun restoreFeedbackFeature(rawString: String?): FeedbackFeature? {
        return restore(rawString)
    }

    @TypeConverter
    fun saveFeedbackFeature(config: FeedbackFeature): String? {
        return save(config)
    }
}
