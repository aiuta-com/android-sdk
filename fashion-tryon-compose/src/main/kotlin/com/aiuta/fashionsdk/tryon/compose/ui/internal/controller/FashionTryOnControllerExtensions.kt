package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.isNotEmpty
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarActionState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarNavigationState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationAppBarState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode

// Configs
internal val skippedBackStackScreens =
    setOf(
        NavigationScreen.SPLASH,
        NavigationScreen.PREONBOARDING,
        NavigationScreen.ONBOARDING,
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
        clickClose(origin = FinishSession.Origin.MAIN_SCREEN)
    }
}

// Active SKU State
internal fun FashionTryOnController.changeActiveSKU(newSkuItem: SKUItem) {
    activeSKUItem.value = newSkuItem
}

// Error State

internal fun FashionTryOnController.showErrorState(
    errorState: FashionTryOnErrorState = FashionTryOnErrorState.DEFAULT,
) {
    fashionTryOnErrorState.value = errorState
}

internal fun FashionTryOnController.hideErrorState() {
    fashionTryOnErrorState.value = null
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
    generationStatus.value = SKUGenerationUIStatus.LOADING
    isGenerationActive.value = true
}

internal fun FashionTryOnController.deactivateGeneration() {
    isGenerationActive.value = false
}

// Generations
internal inline fun <reified T : SKUGenerationOperation> FashionTryOnController.tryToGetOperations(): List<T> {
    return generationOperations.mapNotNull { it as? T }
}

@Composable
internal fun FashionTryOnController.subscribeToSuccessOperations(): State<List<SKUGenerationOperation.SuccessOperation>> {
    return remember(generationOperations) {
        derivedStateOf { tryToGetOperations() }
    }
}

@Composable
internal fun FashionTryOnController.subscribeToLoadingOperations(): State<List<SKUGenerationOperation.LoadingOperation>> {
    return remember(generationOperations) {
        derivedStateOf { tryToGetOperations() }
    }
}

// Checks
@Composable
internal fun FashionTryOnController.appbarState(): State<NavigationAppBarState> {
    return remember(currentScreen.value) {
        derivedStateOf {
            when (currentScreen.value) {
                NavigationScreen.SPLASH,
                NavigationScreen.PREONBOARDING,
                NavigationScreen.ONBOARDING,
                -> NavigationAppBarState.EMPTY

                NavigationScreen.HISTORY -> NavigationAppBarState.HISTORY

                else -> NavigationAppBarState.GENERAL
            }
        }
    }
}

@Composable
internal fun FashionTryOnController.appbarNavigationState(): State<NavigationAppBarNavigationState> {
    return remember(currentScreen.value) {
        derivedStateOf {
            when (currentScreen.value) {
                NavigationScreen.PREONBOARDING -> NavigationAppBarNavigationState.EMPTY
                else -> NavigationAppBarNavigationState.BACK
            }
        }
    }
}

@Composable
internal fun FashionTryOnController.appbarActionState(): State<NavigationAppBarActionState> {
    val aiutaConfiguration = LocalAiutaConfiguration.current

    return remember(currentScreen.value) {
        derivedStateOf {
            when (currentScreen.value) {
                NavigationScreen.HISTORY -> NavigationAppBarActionState.SELECT_PHOTOS

                NavigationScreen.PREONBOARDING -> NavigationAppBarActionState.CLOSE

                NavigationScreen.SPLASH,
                NavigationScreen.ONBOARDING,
                -> NavigationAppBarActionState.EMPTY

                NavigationScreen.IMAGE_SELECTOR, NavigationScreen.GENERATION_RESULT -> {
                    if (aiutaConfiguration.isHistoryAvailable) {
                        NavigationAppBarActionState.HISTORY
                    } else {
                        NavigationAppBarActionState.EMPTY
                    }
                }
            }
        }
    }
}

@Composable
internal fun FashionTryOnController.isAppbarHistoryAvailable(): State<Boolean> {
    val historyImageCount = generatedImageInteractor.countFlow().collectAsStateWithLifecycle(0)

    return remember(generationStatus.value) {
        derivedStateOf {
            generationStatus.value != SKUGenerationUIStatus.LOADING && historyImageCount.value != 0
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
internal fun FashionTryOnController.isLastSavedPhotoAvailable(): State<Boolean> {
    return remember(lastSavedImages.value) {
        derivedStateOf {
            lastSavedImages.value.isNotEmpty()
        }
    }
}

@Composable
internal fun FashionTryOnController.isErrorStateVisible(): State<Boolean> {
    return remember(fashionTryOnErrorState.value) {
        derivedStateOf {
            fashionTryOnErrorState.value != null
        }
    }
}

@Composable
internal fun FashionTryOnController.isActiveSKUGenerateMoreNotEmpty(): State<Boolean> {
    return remember(activeSKUItem.value) {
        derivedStateOf {
            activeSKUItem.value.generateMoreSKU.orEmpty().isNotEmpty()
        }
    }
}
