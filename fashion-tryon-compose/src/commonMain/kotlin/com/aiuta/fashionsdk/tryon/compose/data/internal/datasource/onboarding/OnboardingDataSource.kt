package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class OnboardingDataSource(
    private val database: AiutaTryOnDatabase,
) {
    private val onboardingMetaQueries by lazy { database.onboardingMetaQueries }

    // Onboarding check
    @OptIn(ExperimentalUuidApi::class)
    suspend fun insertOnboardingMeta() {
        withContext(Dispatchers.IO) {
            onboardingMetaQueries.insert(id = Uuid.random().toString())
        }
    }

    fun count(): Flow<Long> = onboardingMetaQueries
        .count()
        .asFlow()
        .mapToOne(Dispatchers.IO)

    companion object {
        fun getInstance(): OnboardingDataSource = OnboardingDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
