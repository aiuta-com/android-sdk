package com.aiuta.fashionsdk.tryon.compose.data.internal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.dao.GeneratedImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.GeneratedOperationDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.SourceImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.OnboardingEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.SourceImageEntity

private const val DATABASE_VERSION = 3
private const val DATABASE_NAME = "fashionsdk-database"

@Database(
    entities = [
        // Generated images
        GeneratedImageEntity::class,

        // Generation operation
        GeneratedOperationEntity::class,
        SourceImageEntity::class,

        // Onboarding saver
        OnboardingEntity::class,
    ],
    version = DATABASE_VERSION,
    exportSchema = false,
)
internal abstract class AppDatabase : RoomDatabase() {
    // Generated images
    abstract fun generatedImageDao(): GeneratedImageDao

    // Generation operation
    abstract fun generatedOperationDao(): GeneratedOperationDao

    abstract fun sourceImageDao(): SourceImageDao

    // Onboarding saver
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
