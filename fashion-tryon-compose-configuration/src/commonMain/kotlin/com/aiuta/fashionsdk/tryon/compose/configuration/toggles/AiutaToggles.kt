package com.aiuta.fashionsdk.tryon.compose.configuration.toggles

import androidx.compose.runtime.Immutable

/**
 * Toggles some features of the Aiuta SDK behavior
 */
@Immutable
public class AiutaToggles(
    /**
     * Flag which turn on or off possibility to use history flow
     * inside Aiuta Try-On flow.
     *
     * Be careful - if you turn off this flag, all previous
     * generation history will be deleted
     */
    public val isHistoryAvailable: Boolean,
    /**
     * Flag which turn on or off possibility to use add to wishlist option
     */
    public val isWishlistAvailable: Boolean,
    /**
     * Flag which turn on or off possibility to use pre-onboarding flow
     */
    public val isPreOnboardingAvailable: Boolean,
    /**
     * Flag which turn on or off possibility to use share option
     */
    public val isShareAvailable: Boolean,
    /**
     * Flag which turn on or off possibility to execute in background work
     */
    public val isBackgroundExecutionAllowed: Boolean,
    /**
     * Mode of onboarding flow
     */
    public val onboardingMode: AiutaOnboardingMode,
    /**
     * Flag which turn on possibility to generate with models
     */
    public val isTryonWithModelsAvailable: Boolean,
)

public val DefaultAiutaToggles: AiutaToggles by lazy {
    AiutaToggles(
        isHistoryAvailable = true,
        isWishlistAvailable = true,
        isPreOnboardingAvailable = false,
        isShareAvailable = true,
        isBackgroundExecutionAllowed = true,
        onboardingMode = AiutaOnboardingMode.STANDARD,
        isTryonWithModelsAvailable = true,
    )
}
