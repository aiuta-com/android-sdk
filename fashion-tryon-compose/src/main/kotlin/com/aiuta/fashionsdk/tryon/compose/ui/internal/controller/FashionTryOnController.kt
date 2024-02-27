package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.GeneratedImageInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.FashionTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUMetaInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.defaultStartScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.rememberZoomImageController
import com.aiuta.fashionsdk.tryon.core.FashionTryOn
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import java.util.LinkedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
internal fun BoxWithConstraintsScope.rememberFashionTryOnController(
    fashionTryOn: () -> FashionTryOn,
    fashionTryOnListeners: () -> FashionTryOnListeners,
    skuForGeneration: () -> SKUGenerationItem,
    skuMetaInfo: () -> SKUMetaInfo,
): FashionTryOnController {
    val context = LocalContext.current

    val zoomImageController =
        rememberZoomImageController(
            constraints = constraints,
        )

    val defaultCurrentScreen =
        remember {
            mutableStateOf(defaultStartScreen())
        }
    val defaultLastSavedUriPhoto =
        remember {
            mutableStateOf<List<String>>(emptyList())
        }
    val defaultIsGenerationActive =
        remember {
            mutableStateOf(false)
        }

    val defaultSelectorState =
        remember {
            mutableStateOf(SelectorMode.DISABLED)
        }

    val defaultBottomSheetNavigator = rememberBottomSheetNavigator()

    return remember {
        FashionTryOnController(
            currentScreen = defaultCurrentScreen,
            bottomSheetNavigator = defaultBottomSheetNavigator,
            selectorState = defaultSelectorState,
            zoomImageController = zoomImageController,
            lastSavedPhotoUris = defaultLastSavedUriPhoto,
            skuForGeneration = skuForGeneration,
            skuMetaInfo = skuMetaInfo,
            fashionTryOn = fashionTryOn,
            fashionTryOnListeners = fashionTryOnListeners,
            isGenerationActive = defaultIsGenerationActive,
            generatedImageInteractor = GeneratedImageInteractor.getInstance(context),
        )
    }
}

@Immutable
internal class FashionTryOnController(
    // General navigation
    public val currentScreen: MutableState<NavigationScreen>,
    internal val backStack: LinkedList<NavigationScreen> = LinkedList(),
    public val zoomImageController: ZoomImageController,
    // Bottom sheet navigation
    public val bottomSheetNavigator: BottomSheetNavigator,
    // Edit mode
    internal val selectorState: MutableState<SelectorMode>,
    val selectorHolder: SelectedHolder<GeneratedImage> = SelectedHolder(),
    // Data
    public val lastSavedPhotoUris: MutableState<List<String>>,
    public val skuForGeneration: () -> SKUGenerationItem,
    public val skuMetaInfo: () -> SKUMetaInfo,
    // Domain
    public val fashionTryOn: () -> FashionTryOn,
    public val fashionTryOnListeners: () -> FashionTryOnListeners,
    public val isGenerationActive: MutableState<Boolean>,
    internal val generatedImageInteractor: GeneratedImageInteractor,
) {
    // Utils
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        scope.launch {
            fashionTryOn()
                .skuGenerationStatus
                .onEach { status ->
                    if (status is SKUGenerationStatus.SuccessGenerationStatus) {
                        generatedImageInteractor.insertAll(status.imageUrls)
                    }
                }
                .collect()
        }
    }
}
