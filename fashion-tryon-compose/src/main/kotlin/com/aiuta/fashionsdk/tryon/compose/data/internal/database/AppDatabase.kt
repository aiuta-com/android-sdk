package com.aiuta.fashionsdk.tryon.compose.data.internal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.code.dao.AiutaCodeDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.code.dao.replaceAll
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.dao.GeneratedImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.GeneratedOperationDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.dao.SourceImageDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.AiutaCodeEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedOperationEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.OnboardingEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.SourceImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

private const val DATABASE_VERSION = 5
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

        // Aiuta code checker
        AiutaCodeEntity::class,
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

    // Aiuta code checker
    abstract fun aiutaCodeDao(): AiutaCodeDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val mutex = Mutex()

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

        suspend fun validateCache(aiuta: Aiuta) {
            mutex.withLock {
                withContext(Dispatchers.IO) {
                    val database = getInstance(aiuta.application)
                    val aiutaCodeDao = database.aiutaCodeDao()
                    val cachedCode = aiutaCodeDao.getCodes().firstOrNull()?.apiKey

                    // Invalidate all records, if we have new Aiuta instance
                    if (cachedCode != aiuta.apiKey) {
                        database.withTransaction {
                            // Delete all records
                            database.generatedOperationDao().removeAll()
                            database.generatedImageDao().removeAll()
                            database.sourceImageDao().removeAll()

                            // Update code
                            aiutaCodeDao.replaceAll(
                                aiutaCodeEntity =
                                    AiutaCodeEntity(
                                        apiKey = aiuta.apiKey,
                                    ),
                            )
                        }
                    }
                }
            }
        }
    }
}
