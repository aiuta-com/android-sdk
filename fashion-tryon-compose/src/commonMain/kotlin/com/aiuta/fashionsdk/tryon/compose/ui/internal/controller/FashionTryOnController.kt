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
import com.aiuta.fashionsdk.configuration.features.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.configuration.features.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.models.product.ProductItem
import com.aiuta.fashionsdk.configuration.ui.AiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images.GeneratedImageInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations.GeneratedOperationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.OnboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.onboarding.onboardingInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.session.SessionGenerationInteractor
import com.aiuta.fashionsdk.tryon.compose.domain.internal.selector.SelectedHolder
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.ProductGenerationOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.ProductGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.bottomsheet.BottomSheetNavigator
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.bottomsheet.rememberBottomSheetNavigator
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.defaultStartScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.models.SelectorMode
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.ZoomImageController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller.rememberZoomImageController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.isFeatureInitialize
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@Composable
internal fun BoxWithConstraintsScope.rememberFashionTryOnController(
    aiutaTryOnConfiguration: AiutaTryOnConfiguration,
    aiutaUserInterfaceConfiguration: AiutaUserInterfaceConfiguration,
    productItem: ProductItem,
): FashionTryOnController {
    val coilContext = LocalPlatformContext.current

    val uploadsHistoryFeature =
        aiutaTryOnConfiguration.features.provideFeature<AiutaImageSelectorUploadsHistoryFeature>()
    val generationsHistoryFeature =
        aiutaTryOnConfiguration.features.provideFeature<AiutaTryOnGenerationsHistoryFeature>()
    val tryOnFeature = aiutaTryOnConfiguration.features.strictProvideFeature<AiutaTryOnFeature>()

    val activeProductItem = remember { mutableStateOf(productItem) }

    val zoomImageController = rememberZoomImageController(constraints = constraints)

    val defaultBottomSheetNavigator = rememberBottomSheetNavigator()

    return remember {
        FashionTryOnController(
            bottomSheetNavigator = defaultBottomSheetNavigator,
            zoomImageController = zoomImageController,
            activeProductItem = activeProductItem,
            aiuta = aiutaTryOnConfiguration.aiuta,
            aiutaTryOn = aiutaTryOnConfiguration.aiutaTryOn,
            aiutaUserInterfaceActions = aiutaUserInterfaceConfiguration.actions,
            generatedImageInteractor = GeneratedImageInteractor.getInstance(
                aiuta = aiutaTryOnConfiguration.aiuta,
                generationsHistoryFeature = generationsHistoryFeature,
            ),
            generatedOperationInteractor = GeneratedOperationInteractor.getInstance(
                aiuta = aiutaTryOnConfiguration.aiuta,
                uploadsHistoryFeature = uploadsHistoryFeature,
            ),
            onboardingInteractor = aiutaTryOnConfiguration.aiuta.onboardingInteractor,
            sessionGenerationInteractor = SessionGenerationInteractor.getInstance(
                coilContext = coilContext,
                generationsHistoryFeature = generationsHistoryFeature,
            ),
            analytic = aiutaTryOnConfiguration.aiutaAnalytic,
        )
    }.also {
        it.generationNavigationListener()
        it.generationCancellationListener(
            tryOnFeature = tryOnFeature,
        )
        it.historyAvailabilityListener(
            isGenerationsHistoryFeatureAvailable = aiutaTryOnConfiguration.isFeatureInitialize<AiutaTryOnGenerationsHistoryFeature>(),
        )
    }
}

@Immutable
internal class FashionTryOnController(
    // General navigation
    public val zoomImageController: ZoomImageController,
    // Bottom sheet navigation
    public val bottomSheetNavigator: BottomSheetNavigator,
    // Data
    public val activeProductItem: MutableState<ProductItem>,
    // Domain
    public val aiuta: Aiuta,
    public val aiutaTryOn: AiutaTryOn,
    public val aiutaUserInterfaceActions: AiutaUserInterfaceActions,
    internal val generatedImageInteractor: GeneratedImageInteractor,
    internal val generatedOperationInteractor: GeneratedOperationInteractor,
    internal val onboardingInteractor: OnboardingInteractor,
    internal val sessionGenerationInteractor: SessionGenerationInteractor,
    // Analytic
    internal val analytic: InternalAiutaAnalytic,
) {
    // Generation state
    internal val generationStatus: MutableState<ProductGenerationUIStatus> =
        mutableStateOf(ProductGenerationUIStatus.IDLE)
    internal val generationOperations: SnapshotStateList<ProductGenerationOperation> =
        mutableStateListOf()

    // General navigation
    internal val backStack: ArrayDeque<NavigationScreen> = ArrayDeque()
    public val currentScreen: MutableState<NavigationScreen> = mutableStateOf(defaultStartScreen())

    // Error state
    public val fashionTryOnErrorState: MutableState<ToastErrorState?> = mutableStateOf(null)

    // Edit changePhotoButtonStyle
    internal val selectorState: MutableState<SelectorMode> = mutableStateOf(SelectorMode.DISABLED)
    val selectorHolder: SelectedHolder<GeneratedImageUIModel> = SelectedHolder()

    // Data
    public val lastSavedImages: MutableState<LastSavedImages> = mutableStateOf(LastSavedImages.Empty)
    public val lastSavedOperation: MutableState<GeneratedOperationUIModel?> = mutableStateOf(null)

    // Domain
    public val isGenerationActive: MutableState<Boolean> = mutableStateOf(false)

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
