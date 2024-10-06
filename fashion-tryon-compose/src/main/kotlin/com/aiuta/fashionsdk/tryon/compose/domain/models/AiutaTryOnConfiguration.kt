package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
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
     *
     * By default, we will look for the language of the device - if SDK supports it,
     * we will use it, otherwise we will use English.
     */
    public val language: AiutaTryOnLanguage?

    /**
     * Possible url of legal disclaimer, which will shows in
     * onboarding flow
     */
    @Deprecated("Will get legal disclaimer text from language settings")
    public val legalDisclaimerUrl: String?

    /**
     * The maximum number of photos that a user can select
     * in the system picket for virtual try on.
     */
    public val photoSelectionLimit: Int

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

    // //////////////////////
    // TODO Refactor configuration
    // Onboarding
    public val isPreOnboardingAvailable: Boolean

    public val isOnboardingAppBarExtended: Boolean

    // Main
    public val isMainAppbarReversed: Boolean
}

public fun defaultAiutaTryOnConfiguration(
    language: AiutaTryOnLanguage? = null,
    legalDisclaimerUrl: String? = null,
    photoSelectionLimit: Int = 10,
    isHistoryAvailable: Boolean = true,
    isWishlistAvailable: Boolean = true,
    // TODO Refactor configuration
    isPreOnboardingAvailable: Boolean = false,
    isOnboardingAppBarExtended: Boolean = false,
    isMainAppbarReversed: Boolean = false,
): AiutaTryOnConfiguration {
    return object : AiutaTryOnConfiguration {
        override val language: AiutaTryOnLanguage? = language
        override val legalDisclaimerUrl: String? = legalDisclaimerUrl
        override val photoSelectionLimit: Int = photoSelectionLimit
        override val isHistoryAvailable: Boolean = isHistoryAvailable
        override val isWishlistAvailable: Boolean = isWishlistAvailable
        override val isPreOnboardingAvailable: Boolean = isPreOnboardingAvailable
        override val isOnboardingAppBarExtended: Boolean = isOnboardingAppBarExtended
        override val isMainAppbarReversed: Boolean = isMainAppbarReversed
    }
}
