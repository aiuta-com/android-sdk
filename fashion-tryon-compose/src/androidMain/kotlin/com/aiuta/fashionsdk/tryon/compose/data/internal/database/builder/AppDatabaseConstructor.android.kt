package com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder

import androidx.room.Room
import androidx.room.RoomDatabase
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.DATABASE_NAME

internal actual fun getDatabaseBuilder(
    platformContext: AiutaPlatformContext,
): RoomDatabase.Builder<AppDatabase> {
    val application = platformContext.application
    val dbFile = application.getDatabasePath(DATABASE_NAME)

    return Room.databaseBuilder<AppDatabase>(
        context = application,
        name = dbFile.absolutePath,
    )
}
