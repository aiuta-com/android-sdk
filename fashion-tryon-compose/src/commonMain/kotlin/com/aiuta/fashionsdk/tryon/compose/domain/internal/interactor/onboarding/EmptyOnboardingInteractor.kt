package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class EmptyOnboardingInteractor : OnboardingInteractor {

    override val isOnboardingCompleted: StateFlow<Boolean> = MutableStateFlow(false)

    override suspend fun completeOnboarding() {
        error(
            message = """
            You are trying to call onboarding data provider without initialization.
            Please, add AiutaOnboardingFeatureDataProvider in AiutaOnboardingFeature to use this functionality.
            """.trimIndent(),
        )
    }
}
