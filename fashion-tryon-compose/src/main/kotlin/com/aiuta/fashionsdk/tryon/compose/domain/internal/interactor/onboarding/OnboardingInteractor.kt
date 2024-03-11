package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.OnboardingDataSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.OnboardingEntity

internal class OnboardingInteractor(
    private val onboardingDataSource: OnboardingDataSource,
) {
    suspend fun shouldShowOnboarding(): Boolean {
        return onboardingDataSource.count() <= 0
    }

    suspend fun setOnboardingAsFinished() {
        onboardingDataSource.insert(OnboardingEntity())
    }

    companion object {
        fun getInstance(context: Context): OnboardingInteractor {
            return OnboardingInteractor(
                onboardingDataSource = OnboardingDataSource.getInstance(context),
            )
        }
    }
}
