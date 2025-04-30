package com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider

import kotlinx.coroutines.flow.StateFlow

public interface AiutaOnboardingFeatureDataProvider {
    public val isOnboardingCompleted: StateFlow<Boolean>
    public fun completeOnboarding()
}
