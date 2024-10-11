package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.dimensions.AiutaDimensions
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

/**
 * Configuration of Aiuta Try on Flow
 *
 * @see [AiutaTryOnFlow]
 */
@Immutable
public interface AiutaTryOnConfiguration {
    /**
     * Language of Aiuta Try on Flow
     */
    public val language: AiutaTryOnLanguage

    /**
     * Dimensions configuration for [AiutaTryOnFlow]
     */
    public val dimensions: AiutaDimensions?

    /**
     * Flag which turn on or off possibility to use history flow
     * inside [AiutaTryOnFlow].
     *
     * Be careful - if you turn off this flag, all previous
     * generation history will be deleted
     */
    public val isHistoryAvailable: Boolean

    /**
     * Flag which turn on or off possibility to use add to wishlist option
     */
    public val isWishlistAvailable: Boolean

    /**
     * Flag which turn on or off possibility to use pre-onboarding flow
     */
    public val isPreOnboardingAvailable: Boolean
}

public fun defaultAiutaTryOnConfiguration(
    language: AiutaTryOnLanguage,
    dimensions: AiutaDimensions? = null,
    isHistoryAvailable: Boolean = true,
    isWishlistAvailable: Boolean = true,
    isPreOnboardingAvailable: Boolean = false,
): AiutaTryOnConfiguration {
    return object : AiutaTryOnConfiguration {
        override val language: AiutaTryOnLanguage = language
        override val dimensions: AiutaDimensions? = dimensions
        override val isHistoryAvailable: Boolean = isHistoryAvailable
        override val isWishlistAvailable: Boolean = isWishlistAvailable
        override val isPreOnboardingAvailable: Boolean = isPreOnboardingAvailable
    }
}
