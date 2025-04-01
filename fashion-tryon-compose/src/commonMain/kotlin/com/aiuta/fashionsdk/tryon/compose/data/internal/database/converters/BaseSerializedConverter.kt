package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import kotlinx.serialization.json.Json

internal abstract class BaseSerializedConverter {
    inline fun <reified T> restore(rawString: String?): T? = rawString?.let { Json.decodeFromString<T>(it) }

    inline fun <reified T> save(item: T?): String? = Json.encodeToString(item)
}
