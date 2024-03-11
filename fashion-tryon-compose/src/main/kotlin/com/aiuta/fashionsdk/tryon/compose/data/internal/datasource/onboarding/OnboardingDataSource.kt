package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao.OnboardingDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.OnboardingEntity
import kotlinx.coroutines.Dispatchers
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
        fun getInstance(context: Context): OnboardingDataSource {
            return OnboardingDataSource(
                onboardingDao = AppDatabase.getInstance(context).onboardingDao(),
            )
        }
    }
}
