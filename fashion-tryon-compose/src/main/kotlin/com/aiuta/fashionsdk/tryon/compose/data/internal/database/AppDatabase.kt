package com.aiuta.fashionsdk.tryon.compose.data.internal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generatedimages.dao.GeneratedImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.OnboardingEntity

private const val DATABASE_VERSION = 2
private const val DATABASE_NAME = "fashionsdk-database"

@Database(
    entities = [
        GeneratedImageEntity::class,
        OnboardingEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false,
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun generatedImageDao(): GeneratedImageDao

    abstract fun onboardingDao(): OnboardingDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var currentInstance = instance

                if (currentInstance == null) {
                    currentInstance =
                        Room
                            .databaseBuilder(
                                context = context.applicationContext,
                                klass = AppDatabase::class.java,
                                name = DATABASE_NAME,
                            )
                            // Fallback
                            .fallbackToDestructiveMigration()
                            .build()

                    instance = currentInstance
                }
                return currentInstance
            }
        }
    }
}
