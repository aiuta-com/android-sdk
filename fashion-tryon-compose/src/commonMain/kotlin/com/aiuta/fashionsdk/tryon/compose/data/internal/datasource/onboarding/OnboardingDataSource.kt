package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class OnboardingDataSource(
    private val database: AiutaTryOnDatabase,
) {
    private val onboardingMetaQueries by lazy { database.onboardingMetaQueries }

    // Onboarding check
    suspend fun insertOnboardingMeta() {
        withContext(Dispatchers.IO) {
            onboardingMetaQueries.insert(id = null)
        }
    }

    fun getOnboardingEntitiesFlow(): Flow<List<Long>> = onboardingMetaQueries
        .select()
        .asFlow()
        .mapToList(Dispatchers.IO)

    companion object {
        fun getInstance(): OnboardingDataSource = OnboardingDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
