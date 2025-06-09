package com.aiuta.fashionsdk.tryon.compose.data.internal.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.aiuta.fashionsdk.context.AiutaPlatformContext

internal actual fun createDriver(platformContext: AiutaPlatformContext): SqlDriver = NativeSqliteDriver(
    schema = AiutaTryOnDatabase.Schema,
    name = "fashionsdk-database.db",
)
