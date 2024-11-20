package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.isNotEmpty
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toLastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode

// Configs
internal val skippedBackStackScreens =
    setOf(
        NavigationScreen.Splash,
        NavigationScreen.Onboarding,
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

internal fun FashionTryOnController.popUpAndNavigateTo(
    navigateToScreen: NavigationScreen,
    popUpScreen: NavigationScreen? = null,
) {
    // Remove all screens including popUpScreen
    while (true) {
        val previousScreen = backStack.pop()
        if (popUpScreen == null || previousScreen == popUpScreen) {
            break
        }
    }

    navigateTo(navigateToScreen)
}

internal fun FashionTryOnController.navigateBack(
    origin: FinishSession.Origin = FinishSession.Origin.MAIN_SCREEN,
) {
    if (backStack.isNotEmpty()) {
        val previousScreen = backStack.pop()

        currentScreen.value = previousScreen
    } else {
        clickClose(origin = origin)
    }
}

// Active SKU State
internal fun FashionTryOnController.changeActiveSKU(newSkuItem: SKUItem) {
    activeSKUItem.value = newSkuItem
}

// Error State

internal fun FashionTryOnController.showErrorState(errorState: ToastErrorState) {
    // Check if toast already visible
    if (fashionTryOnErrorState.value == null) {
        fashionTryOnErrorState.value = errorState
    }
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

// Auto try on mode
internal fun FashionTryOnController.activateAutoTryOn() {
    isAutoTryOnEnabled.value = true
}

internal fun FashionTryOnController.disableAutoTryOn() {
    isAutoTryOnEnabled.value = false
}

internal fun FashionTryOnController.updateActiveOperationOrSetEmpty(
    operation: GeneratedOperationUIModel?,
) {
    // Try to update with new or set as empty
    if (operation != null) {
        lastSavedOperation.value = operation
        lastSavedImages.value = operation.toLastSavedImages()
    } else {
        lastSavedOperation.value = null
        lastSavedImages.value = LastSavedImages.Empty
    }
}

internal suspend fun FashionTryOnController.updateActiveOperationWithFirstOrSetEmpty() {
    val firstOperation = generatedOperationInteractor.getFirstGeneratedOperation()
    // Try to update with new or set as empty
    if (firstOperation != null) {
        lastSavedOperation.value = firstOperation
        lastSavedImages.value = firstOperation.toLastSavedImages()
    } else {
        lastSavedOperation.value = null
        lastSavedImages.value = LastSavedImages.Empty
    }
}

// Checks
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
