package com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AIUTA_TRYON_DATABASE_NAME

internal actual suspend fun createSQLDelightDriver(
    platformContext: AiutaPlatformContext,
    schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
): SqlDriver = JdbcSqliteDriver(
    url = "jdbc:sqlite:$AIUTA_TRYON_DATABASE_NAME",
    schema = schema.synchronous(),
)
