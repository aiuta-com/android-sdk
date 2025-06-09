package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.consent

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class ConsentDataSource(
    private val database: AiutaTryOnDatabase,
) {
    private val obtainedConsentQueries by lazy { database.obtainedConsentQueries }

    suspend fun insertObtainedConsent(consentIds: List<String>) = withContext(Dispatchers.IO) {
        obtainedConsentQueries.transaction {
            consentIds.forEach { id ->
                obtainedConsentQueries.insert(id)
            }
        }
    }

    fun obtainedConsentsFlow(): Flow<List<String>> = obtainedConsentQueries
        .selectAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    companion object {
        fun getInstance(): ConsentDataSource = ConsentDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
