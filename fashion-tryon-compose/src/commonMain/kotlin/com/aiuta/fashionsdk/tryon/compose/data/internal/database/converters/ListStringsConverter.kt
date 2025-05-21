package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
internal class ListStringsConverter : BaseSerializedConverter() {
    @TypeConverter
    fun restoreListStrings(rawString: String?): List<String>? = restore(rawString)

    @TypeConverter
    fun saveListStrings(config: List<String>): String? = save(config)
}
