package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.ConsentDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.ConsentEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.OnboardingEntity

internal class OnboardingDataSource(
    private val onboardingDao: OnboardingDao,
    private val consentDao: ConsentDao,
) {
    // Onboarding check
    suspend fun insertOnboardingEntity(entity: OnboardingEntity) = onboardingDao.insert(entity)

    suspend fun getOnboardingEntity(): OnboardingEntity? = onboardingDao.getOnboardingEntity()

    // Consents
    suspend fun insertConsents(consents: List<ConsentEntity>) = consentDao.insert(consents)

    suspend fun getConsentIds(): List<String> = consentDao.getConsentIds()

    suspend fun getObtainedConsentIds(): List<String> = consentDao.getObtainedConsentIds()

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): OnboardingDataSource = OnboardingDataSource(
            onboardingDao = AppDatabase.getInstance(platformContext).onboardingDao(),
            consentDao = AppDatabase.getInstance(platformContext).consentDao(),
        )
    }
}
