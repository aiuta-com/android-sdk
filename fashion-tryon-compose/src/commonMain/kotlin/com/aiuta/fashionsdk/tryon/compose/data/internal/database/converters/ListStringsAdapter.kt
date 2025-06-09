package com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters

import app.cash.sqldelight.ColumnAdapter

internal class ListStringsAdapter : ColumnAdapter<List<String>, String> {
    override fun decode(databaseValue: String): List<String> = if (databaseValue.isEmpty()) {
        listOf()
    } else {
        databaseValue.split(",")
    }

    override fun encode(value: List<String>): String = value.joinToString(separator = ",")
}
