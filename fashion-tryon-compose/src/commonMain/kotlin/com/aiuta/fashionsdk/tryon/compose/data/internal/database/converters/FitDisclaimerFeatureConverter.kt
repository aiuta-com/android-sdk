package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.FitDisclaimerFeature

@ProvidedTypeConverter
internal class FitDisclaimerFeatureConverter : BaseSerializedConverter() {
    @TypeConverter
    fun restoreFitDisclaimerFeature(rawString: String?): FitDisclaimerFeature? = restore(rawString)

    @TypeConverter
    fun saveFitDisclaimerFeature(config: FitDisclaimerFeature): String? = save(config)
}
