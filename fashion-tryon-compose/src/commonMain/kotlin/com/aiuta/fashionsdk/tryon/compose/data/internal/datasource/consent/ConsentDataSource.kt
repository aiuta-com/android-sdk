package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.consent

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.ConsentDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.consent.ObtainedConsentEntity
import kotlinx.coroutines.flow.Flow

internal class ConsentDataSource(
    private val consentDao: ConsentDao,
) {
    suspend fun insertObtainedConsent(consentIds: List<String>) {
        consentDao.insert(
            entities = consentIds.map { ObtainedConsentEntity(id = it) },
        )
    }

    fun obtainedConsentsFlow(): Flow<List<ObtainedConsentEntity>> = consentDao.getObtainedConsentsFlow()

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): ConsentDataSource = ConsentDataSource(
            consentDao = AppDatabase.getInstance(platformContext).consentDao(),
        )
    }
}
