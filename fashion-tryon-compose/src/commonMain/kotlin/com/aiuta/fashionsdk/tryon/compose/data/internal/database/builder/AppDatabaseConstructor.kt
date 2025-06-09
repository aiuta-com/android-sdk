package com.aiuta.fashionsdk.tryon.compose.data.internal.database.builder

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.ListStringsConverter
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.converters.TryOnModelsCategoriesConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Deprecated("Migrate to SQLDelight builders")
internal expect fun getDatabaseBuilder(
    platformContext: AiutaPlatformContext,
): RoomDatabase.Builder<AppDatabase>

@Deprecated("Migrate to SQLDelight builders")
internal fun buildRoomDatabase(platformContext: AiutaPlatformContext): AppDatabase = getDatabaseBuilder(platformContext)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    // Config
    .addTypeConverter(TryOnModelsCategoriesConverter())
    .addTypeConverter(ListStringsConverter())
    // Fallback
    .fallbackToDestructiveMigration(true)
    .build()
