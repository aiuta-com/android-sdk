package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.OnboardingEntity

internal class OnboardingDataSource(
    private val onboardingDao: OnboardingDao,
) {
    // Onboarding check
    suspend fun insertOnboardingEntity(entity: OnboardingEntity) = onboardingDao.insert(entity)

    suspend fun getOnboardingEntity(): OnboardingEntity? = onboardingDao.getOnboardingEntity()

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): OnboardingDataSource = OnboardingDataSource(
            onboardingDao = AppDatabase.getInstance(platformContext).onboardingDao(),
        )
    }
}
