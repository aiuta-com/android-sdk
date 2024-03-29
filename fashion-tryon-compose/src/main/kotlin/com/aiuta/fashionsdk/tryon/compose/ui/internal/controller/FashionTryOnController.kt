package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generatedimages.GeneratedImageInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.OnboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.defaultStartScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.rememberZoomImageController
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
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
    analytic: () -> InternalAiutaAnalytic,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    skuForGeneration: () -> SKUItem,
): FashionTryOnController {
    val context = LocalContext.current

    val activeSKUItem =
        remember {
            mutableStateOf(skuForGeneration())
        }

    val zoomImageController =
        rememberZoomImageController(
            constraints = constraints,
        )
    val defaultSKUItemVisibility =
        remember {
            mutableStateOf(false)
        }

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

    val defaultFashionTryOnErrorState =
        remember {
            mutableStateOf<FashionTryOnErrorState?>(null)
        }

    val defaultBottomSheetNavigator = rememberBottomSheetNavigator()

    return remember {
        FashionTryOnController(
            currentScreen = defaultCurrentScreen,
            bottomSheetNavigator = defaultBottomSheetNavigator,
            fashionTryOnErrorState = defaultFashionTryOnErrorState,
            selectorState = defaultSelectorState,
            zoomImageController = zoomImageController,
            isSKUItemVisible = defaultSKUItemVisibility,
            lastSavedPhotoUris = defaultLastSavedUriPhoto,
            activeSKUItem = activeSKUItem,
            aiutaTryOn = aiutaTryOn,
            aiutaTryOnListeners = aiutaTryOnListeners,
            isGenerationActive = defaultIsGenerationActive,
            generatedImageInteractor = GeneratedImageInteractor.getInstance(context),
            onboardingInteractor = OnboardingInteractor.getInstance(context),
            analytic = analytic(),
        )
    }.also {
        it.skuItemVisibilityListener()
    }
}

@Immutable
internal class FashionTryOnController(
    // General navigation
    public val currentScreen: MutableState<NavigationScreen>,
    internal val backStack: LinkedList<NavigationScreen> = LinkedList(),
    public val zoomImageController: ZoomImageController,
    public val isSKUItemVisible: MutableState<Boolean>,
    // Bottom sheet navigation
    public val bottomSheetNavigator: BottomSheetNavigator,
    // Error state
    public val fashionTryOnErrorState: MutableState<FashionTryOnErrorState?>,
    // Edit mode
    internal val selectorState: MutableState<SelectorMode>,
    val selectorHolder: SelectedHolder<GeneratedImage> = SelectedHolder(),
    // Interface z index
    val zIndexInterface: Float = 2f,
    // Data
    public val lastSavedPhotoUris: MutableState<List<String>>,
    public val activeSKUItem: MutableState<SKUItem>,
    // Domain
    public val aiutaTryOn: () -> AiutaTryOn,
    public val aiutaTryOnListeners: () -> AiutaTryOnListeners,
    public val isGenerationActive: MutableState<Boolean>,
    internal val generatedImageInteractor: GeneratedImageInteractor,
    internal val onboardingInteractor: OnboardingInteractor,
    // Analytic
    internal val analytic: InternalAiutaAnalytic,
) {
    // Utils
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        scope.launch {
            aiutaTryOn()
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
