package com.aiuta.fashionsdk.compose.tokens.toggles

import androidx.compose.runtime.Immutable

/**
 * Public class for toggling various theme settings in the SDK.
 */
@Immutable
public class AiutaThemeToggles(
    /**
     * Flag which turn on or off possibility to extend
     * the onboarding app bar with close button
     */
    public val isOnboardingAppBarExtended: Boolean,
    /**
     * Flag which turn on or off possibility to reverse the main app bar
     *
     * If true, history will be on the left side and close button on the right
     */
    public val isMainAppbarReversed: Boolean,
    /**
     * Flag which turn on or off possibility to reduce shadows
     */
    public val isShadowsReduced: Boolean,
    /**
     * Flag which make delimiters in shorter or longer
     * in picker bottom sheet
     */
    public val isDelimitersExtended: Boolean,
    /**
     * Is product first image extended padding applied
     */
    public val isProductFistImageExtendedPaddingApplied: Boolean,
    /**
     * Flag which turn on or off possibility to blur outlines
     */
    public val isBlurOutlinesEnabled: Boolean,
)

public val DefaultAiutaThemeToggles: AiutaThemeToggles by lazy { aiutaThemeToggles() }
