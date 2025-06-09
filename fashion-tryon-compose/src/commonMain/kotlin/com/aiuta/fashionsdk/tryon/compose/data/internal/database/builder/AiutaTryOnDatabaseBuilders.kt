package com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.aiuta.fashionsdk.context.AiutaPlatformContext

internal expect suspend fun createSQLDelightDriver(
    platformContext: AiutaPlatformContext,
    schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
): SqlDriver
