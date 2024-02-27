package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

// Configs
private val screensWithSKUItems =
    setOf(
        NavigationScreen.IMAGE_SELECTOR,
        NavigationScreen.GENERATION_RESULT,
    )

private val skippedBackStackScreens =
    setOf(
        NavigationScreen.SPLASH,
    )

// Navigation
internal fun FashionTryOnController.navigateTo(newScreen: NavigationScreen) {
    // Save previous screen, if we should not skip it in back stack
    if (currentScreen.value !in skippedBackStackScreens) {
        backStack.push(currentScreen.value)
    }

    // Set new screen
    currentScreen.value = newScreen
}

internal fun FashionTryOnController.navigateBack() {
    if (backStack.isNotEmpty()) {
        val previousScreen = backStack.pop()

        currentScreen.value = previousScreen
    } else {
        fashionTryOnListeners().closeClick()
    }
}

// Edit mode
internal fun FashionTryOnController.activateSelectMode() {
    selectorState.value = SelectorMode.ALL_IS_NOT_SELECTED
}

internal fun FashionTryOnController.deactivateSelectMode() {
    selectorState.value = SelectorMode.DISABLED
    selectorHolder.removeAll()
}

// Generation helpers
internal fun FashionTryOnController.activateGeneration() {
    isGenerationActive.value = true
}

internal fun FashionTryOnController.deactivateGeneration() {
    isGenerationActive.value = false
}

// Checks
@Composable
internal fun FashionTryOnController.appbarState(): State<NavigationAppBarState> {
    return remember(currentScreen.value) {
        derivedStateOf {
            when (currentScreen.value) {
                NavigationScreen.SPLASH -> NavigationAppBarState.EMPTY
                NavigationScreen.HISTORY -> NavigationAppBarState.HISTORY
                else -> NavigationAppBarState.GENERAL
            }
        }
    }
}

@Composable
internal fun FashionTryOnController.isAppbarHistoryAvailable(): State<Boolean> {
    val skuGenerationStatus = fashionTryOn().skuGenerationStatus.collectAsStateWithLifecycle()

    return remember(skuGenerationStatus.value) {
        derivedStateOf {
            skuGenerationStatus.value !is SKUGenerationStatus.LoadingGenerationStatus
        }
    }
}

@Composable
internal fun FashionTryOnController.isAppbarSelectAvailable(): State<Boolean> {
    return remember(selectorState.value) {
        derivedStateOf {
            selectorState.value == SelectorMode.DISABLED
        }
    }
}

@Composable
internal fun FashionTryOnController.isSelectModeActive(): State<Boolean> {
    return remember(selectorState.value) {
        derivedStateOf {
            selectorState.value != SelectorMode.DISABLED
        }
    }
}

@Composable
internal fun FashionTryOnController.isSKUItemVisible(): State<Boolean> {
    return remember(currentScreen.value) {
        derivedStateOf {
            currentScreen.value in screensWithSKUItems
        }
    }
}

@Composable
internal fun FashionTryOnController.isLastSavedPhotoAvailable(): State<Boolean> {
    return remember(lastSavedPhotoUris.value) {
        derivedStateOf {
            lastSavedPhotoUris.value.isNotEmpty()
        }
    }
}
