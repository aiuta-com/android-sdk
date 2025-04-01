package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId

/**
 * Be careful, order is matter for animation transitions,
 */
internal interface NavigationScreen {
    val exitPageId: AiutaAnalyticPageId

    object Splash : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.WELCOME
    }

    object Preonboarding : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.WELCOME
    }

    object Onboarding : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.HOW_IT_WORKS
    }

    object ImageSelector : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.IMAGE_PICKER
    }

    object ModelSelector : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.MODEL_PICKER
    }

    object GenerationResult : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.RESULTS
    }

    // Utility screens
    object History : NavigationScreen {
        override val exitPageId: AiutaAnalyticPageId = AiutaAnalyticPageId.HISTORY
    }
}

internal fun defaultStartScreen(): NavigationScreen = NavigationScreen.Splash

// Stack
private val screenStacks =
    listOf(
        NavigationScreen.Splash,
        NavigationScreen.Preonboarding,
        NavigationScreen.Onboarding,
        NavigationScreen.ImageSelector,
        NavigationScreen.ModelSelector,
        NavigationScreen.GenerationResult,
        NavigationScreen.History,
    )

internal fun screenPosition(screen: NavigationScreen): Int = screenStacks.indexOf(screen)
