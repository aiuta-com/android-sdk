package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import coil3.compose.LocalPlatformContext
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images.GeneratedImageInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.OnboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.onboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session.SessionGenerationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Composable
internal fun BoxWithConstraintsScope.rememberFashionTryOnController(
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaTryOnListeners: AiutaTryOnListeners,
    skuForGeneration: SKUItem,
): FashionTryOnController {
    val coilContext = LocalPlatformContext.current

    val uploadsHistoryFeature = aiutaTryOnConfiguration.features.provideFeature<AiutaImageSelectorUploadsHistoryFeature>()
    val generationsHistoryFeature = aiutaTryOnConfiguration.features.provideFeature<AiutaTryOnGenerationsHistoryFeature>()

    val activeSKUItem =
        remember {
            mutableStateOf(skuForGeneration)
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
            mutableStateOf<GeneratedOperationUIModel?>(null)
        }
    val defaultIsGenerationActive =
        remember {
            mutableStateOf(false)
        }

    // Data providers
    // TODO Support empty data providers
    val generatedImageInteractor =
        remember {
            generationsHistoryFeature?.dataProvider?.let { dataProvider ->
                GeneratedImageInteractor.getInstance(dataProvider)
            } ?: GeneratedImageInteractor.getInstance(aiutaTryOnConfiguration.aiuta)
        }
    val generatedOperationInteractor =
        remember {
            uploadsHistoryFeature?.dataProvider?.let { dataProvider ->
                GeneratedOperationInteractor.getInstance(dataProvider)
            } ?: GeneratedOperationInteractor.getInstance(aiutaTryOnConfiguration.aiuta)
        }

    val defaultSelectorState =
        remember {
            mutableStateOf(SelectorMode.DISABLED)
        }

    val defaultFashionTryOnErrorState =
        remember {
            mutableStateOf<ToastErrorState?>(null)
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
            aiuta = aiutaTryOnConfiguration.aiuta,
            aiutaTryOn = aiutaTryOnConfiguration.aiutaTryOn,
            aiutaTryOnListeners = aiutaTryOnListeners,
            isGenerationActive = defaultIsGenerationActive,
            generatedImageInteractor = generatedImageInteractor,
            generatedOperationInteractor = generatedOperationInteractor,
            onboardingInteractor = aiutaTryOnConfiguration.aiuta.onboardingInteractor,
            sessionGenerationInteractor = SessionGenerationInteractor.getInstance(coilContext),
            analytic = aiutaTryOnConfiguration.aiutaAnalytic,
        )
    }.also {
        it.generationNavigationListener()
        it.generationCancellationListener()
        it.historyAvailabilityListener()
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
    internal val backStack: ArrayDeque<NavigationScreen> = ArrayDeque(),
    public val zoomImageController: ZoomImageController,
    // Bottom sheet navigation
    public val bottomSheetNavigator: BottomSheetNavigator,
    // Error state
    public val fashionTryOnErrorState: MutableState<ToastErrorState?>,
    // Edit changePhotoButtonStyle
    internal val selectorState: MutableState<SelectorMode>,
    val selectorHolder: SelectedHolder<GeneratedImageUIModel> = SelectedHolder(),
    // Data
    public val lastSavedImages: MutableState<LastSavedImages>,
    public val lastSavedOperation: MutableState<GeneratedOperationUIModel?>,
    public val activeSKUItem: MutableState<SKUItem>,
    // Domain
    public val aiuta: Aiuta,
    public val aiutaTryOn: AiutaTryOn,
    public val aiutaTryOnListeners: AiutaTryOnListeners,
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

    // Try on auto enabling
    val isAutoTryOnEnabled: MutableState<Boolean> = mutableStateOf(false)

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

    internal fun clean() {
        // Cancel generation scope
        internalGenerationScope?.cancel()
    }
}
