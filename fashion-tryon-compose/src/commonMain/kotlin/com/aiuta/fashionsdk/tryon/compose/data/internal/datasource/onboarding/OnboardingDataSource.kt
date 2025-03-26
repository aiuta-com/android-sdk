package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.OnboardingEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class OnboardingDataSource(
    private val onboardingDao: OnboardingDao,
) {
    suspend fun insert(onboardingEntity: OnboardingEntity) {
        withContext(Dispatchers.IO) {
            onboardingDao.insert(onboardingEntity)
        }
    }

    suspend fun count(): Int {
        return withContext(Dispatchers.IO) {
            onboardingDao.count()
        }
    }

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): OnboardingDataSource {
            return OnboardingDataSource(
                onboardingDao = AppDatabase.getInstance(platformContext).onboardingDao(),
            )
        }
    }
}
