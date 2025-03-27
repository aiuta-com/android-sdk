package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config.features.TryOnModelsCategory

@ProvidedTypeConverter
internal class TryOnModelsCategoriesConverter : BaseSerializedConverter() {
    @TypeConverter
    fun restoreTryOnModelsCategories(rawString: String?): List<TryOnModelsCategory>? {
        return restore(rawString)
    }

    @TypeConverter
    fun saveTryOnModelsCategories(config: List<TryOnModelsCategory>): String? {
        return save(config)
    }
}
