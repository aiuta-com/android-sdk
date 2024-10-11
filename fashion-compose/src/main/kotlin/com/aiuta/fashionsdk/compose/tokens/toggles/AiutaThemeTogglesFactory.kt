package com.aiuta.fashionsdk.compose.tokens.toggles

/**
 * Creates an instance of [AiutaThemeToggles]
 *
 * @return An instance of [AiutaThemeToggles] with the specified or default theme toggles.
 * @see AiutaThemeToggles
 */
public fun aiutaThemeToggles(
    isOnboardingAppBarExtended: Boolean = false,
    isMainAppbarReversed: Boolean = false,
    isShadowsReduced: Boolean = false,
    isDelimitersExtended: Boolean = false,
): AiutaThemeToggles {
    return AiutaThemeToggles(
        isOnboardingAppBarExtended = isOnboardingAppBarExtended,
        isMainAppbarReversed = isMainAppbarReversed,
        isShadowsReduced = isShadowsReduced,
        isDelimitersExtended = isDelimitersExtended,
    )
}
