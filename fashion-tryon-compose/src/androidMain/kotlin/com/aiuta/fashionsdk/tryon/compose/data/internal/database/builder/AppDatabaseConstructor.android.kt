package com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder

import androidx.room.Room
import androidx.room.RoomDatabase
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.ANDROID_DATABASE_NAME
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase

internal actual fun getDatabaseBuilder(
    platformContext: AiutaPlatformContext,
): RoomDatabase.Builder<AppDatabase> {
    val dbFile = platformContext.getDatabasePath(ANDROID_DATABASE_NAME)

    return Room.databaseBuilder<AppDatabase>(
        context = platformContext,
        name = dbFile.absolutePath,
    )
}
