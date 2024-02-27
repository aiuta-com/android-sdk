package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

/**
 * Be careful, order is matter for animation transitions,
 */
public enum class NavigationScreen {
    SPLASH,

    IMAGE_SELECTOR,

    GENERATION_RESULT,

    // Utility screens
    HISTORY,
}

public fun defaultStartScreen(): NavigationScreen {
    return NavigationScreen.SPLASH
}
