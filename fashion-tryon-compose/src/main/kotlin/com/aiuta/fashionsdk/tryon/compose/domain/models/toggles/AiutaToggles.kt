package com.aiuta.fashionsdk.tryon.compose.domain.models.toggles

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

/**
 * Toggles some features of the Aiuta SDK behavior
 */
@Immutable
public class AiutaToggles(
    /**
     * Flag which turn on or off possibility to use history flow
     * inside [AiutaTryOnFlow].
     *
     * Be careful - if you turn off this flag, all previous
     * generation history will be deleted
     */
    public val isHistoryAvailable: Boolean = true,
    /**
     * Flag which turn on or off possibility to use add to wishlist option
     */
    public val isWishlistAvailable: Boolean = true,
    /**
     * Flag which turn on or off possibility to use pre-onboarding flow
     */
    public val isPreOnboardingAvailable: Boolean = false,
    /**
     * Flag which turn on or off possibility to use share option
     */
    public val isShareAvailable: Boolean = true,
)
