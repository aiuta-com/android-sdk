package com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider

import kotlinx.coroutines.flow.StateFlow

/**
 * Custom implementation of the `AiutaOnboardingFeatureDataProvider` interface.
 * Provides logic for managing the onboarding process state.
 */
public interface AiutaOnboardingFeatureDataProviderCustom : AiutaOnboardingFeatureDataProvider {
    /**
     * A [StateFlow] that emits the completion status of the onboarding process.
     * Emits `true` if the onboarding process is completed, otherwise `false`.
     */
    public val isOnboardingCompleted: StateFlow<Boolean>

    /**
     * Marks the onboarding process as completed.
     * This method should be called when the onboarding process is successfully finished.
     */
    public suspend fun completeOnboarding()
}
