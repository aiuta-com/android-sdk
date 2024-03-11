package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

/**
 * Be careful, order is matter for animation transitions,
 */
internal enum class NavigationScreen {
    SPLASH,

    ONBOARDING,

    IMAGE_SELECTOR,

    GENERATION_RESULT,

    // Utility screens
    HISTORY,
}

internal fun defaultStartScreen(): NavigationScreen {
    return NavigationScreen.SPLASH
}
