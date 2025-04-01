package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.OnboardingDataSource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.OnboardingEntity

internal val Aiuta.onboardingInteractor: OnboardingInteractor
    get() = OnboardingInteractor.getInstance(platformContext)

internal class OnboardingInteractor(
    private val onboardingDataSource: OnboardingDataSource,
) {
    suspend fun shouldShowOnboarding(): Boolean = onboardingDataSource.count() <= 0

    suspend fun setOnboardingAsFinished() {
        onboardingDataSource.insert(OnboardingEntity())
    }

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): OnboardingInteractor = OnboardingInteractor(
            onboardingDataSource = OnboardingDataSource.getInstance(platformContext),
        )
    }
}
