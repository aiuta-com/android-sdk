package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images.GeneratedImageInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.OnboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session.SessionGenerationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.bottomsheet.BottomSheetNavigator
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.bottomsheet.rememberBottomSheetNavigator
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.defaultStartScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.rememberZoomImageController
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import java.util.LinkedList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Composable
internal fun BoxWithConstraintsScope.rememberFashionTryOnController(
    analytic: () -> InternalAiutaAnalytic,
    aiuta: () -> Aiuta,
    aiutaTryOn: () -> AiutaTryOn,
    aiutaTryOnListeners: () -> AiutaTryOnListeners,
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
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

    val defaultCurrentScreen =
        remember {
            mutableStateOf(defaultStartScreen())
        }
    val defaultLastSavedImages =
        remember {
            mutableStateOf<LastSavedImages>(LastSavedImages.Empty)
        }
    val defaultSavedOperation =
        remember {
            mutableStateOf<GeneratedOperation?>(null)
        }
    val defaultIsGenerationActive =
        remember {
            mutableStateOf(false)
        }

    // Data providers
    val generatedImageInteractor =
        remember {
            aiutaTryOnConfiguration.dataProvider?.let { dataProvider ->
                GeneratedImageInteractor.getInstance(dataProvider)
            } ?: GeneratedImageInteractor.getInstance(context)
        }
    val generatedOperationInteractor =
        remember {
            aiutaTryOnConfiguration.dataProvider?.let { dataProvider ->
                GeneratedOperationInteractor.getInstance(dataProvider)
            } ?: GeneratedOperationInteractor.getInstance(context)
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

    // Generation state
    val defaultGenerationUIStatus =
        remember {
            mutableStateOf(SKUGenerationUIStatus.IDLE)
        }
    val defaultGenerationOperations =
        remember {
            mutableStateListOf<SKUGenerationOperation>()
        }

    return remember {
        FashionTryOnController(
            generationStatus = defaultGenerationUIStatus,
            generationOperations = defaultGenerationOperations,
            currentScreen = defaultCurrentScreen,
            bottomSheetNavigator = defaultBottomSheetNavigator,
            fashionTryOnErrorState = defaultFashionTryOnErrorState,
            selectorState = defaultSelectorState,
            zoomImageController = zoomImageController,
            lastSavedImages = defaultLastSavedImages,
            lastSavedOperation = defaultSavedOperation,
            activeSKUItem = activeSKUItem,
            aiuta = aiuta,
            aiutaTryOn = aiutaTryOn,
            aiutaTryOnListeners = aiutaTryOnListeners,
            isGenerationActive = defaultIsGenerationActive,
            generatedImageInteractor = generatedImageInteractor,
            generatedOperationInteractor = generatedOperationInteractor,
            onboardingInteractor = OnboardingInteractor.getInstance(context),
            sessionGenerationInteractor = SessionGenerationInteractor(context),
            analytic = analytic(),
        )
    }.also {
        it.generationNavigationListener()
        it.historyAvailabilityListener(aiutaTryOnConfiguration)
        it.updationActiveSKUItemListener()
    }
}

@Immutable
internal class FashionTryOnController(
    // Generation state
    internal val generationStatus: MutableState<SKUGenerationUIStatus>,
    internal val generationOperations: SnapshotStateList<SKUGenerationOperation>,
    // General navigation
    public val currentScreen: MutableState<NavigationScreen>,
    internal val backStack: LinkedList<NavigationScreen> = LinkedList(),
    public val zoomImageController: ZoomImageController,
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
    public val lastSavedImages: MutableState<LastSavedImages>,
    public val lastSavedOperation: MutableState<GeneratedOperation?>,
    public val activeSKUItem: MutableState<SKUItem>,
    // Domain
    public val aiuta: () -> Aiuta,
    public val aiutaTryOn: () -> AiutaTryOn,
    public val aiutaTryOnListeners: () -> AiutaTryOnListeners,
    public val isGenerationActive: MutableState<Boolean>,
    internal val generatedImageInteractor: GeneratedImageInteractor,
    internal val generatedOperationInteractor: GeneratedOperationInteractor,
    internal val onboardingInteractor: OnboardingInteractor,
    internal val sessionGenerationInteractor: SessionGenerationInteractor,
    // Analytic
    internal val analytic: InternalAiutaAnalytic,
) {
    // Utils
    private var internalGenerationScope: CoroutineScope? = null
    internal val generationScope: CoroutineScope
        get() {
            return cancelGenerationScope()
        }

    internal val generalScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    /**
     * Cancel current generation scope and set new for next generation
     *
     * @return new coroutine scope
     */
    internal fun cancelGenerationScope(): CoroutineScope {
        internalGenerationScope?.cancel()
        return CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate).also {
            internalGenerationScope = it
        }
    }
}
