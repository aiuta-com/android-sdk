package com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider

import kotlinx.coroutines.flow.StateFlow

/**
 * A sealed interface representing the base type for all onboarding feature data providers.
 */
public sealed interface AiutaOnboardingFeatureDataProvider

/**
 * Interface defining the logic for managing onboarding state.
 */
public interface AiutaOnboardingFeatureDataProviderLogic {
    /**
     * A [StateFlow] that emits the completion status of the onboarding process.
     */
    public val isOnboardingCompleted: StateFlow<Boolean>

    /**
     * Marks the onboarding process as completed.
     */
    public suspend fun completeOnboarding()
}
