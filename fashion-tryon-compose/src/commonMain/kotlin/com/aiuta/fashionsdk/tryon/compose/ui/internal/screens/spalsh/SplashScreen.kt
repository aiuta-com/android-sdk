package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.preloadConfig
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.updateActiveOperationWithFirstOrSetEmpty
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.validateControllerCache
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val dataController = LocalAiutaTryOnDataController.current

    LaunchedEffect(Unit) {
        // Try to preload config
        launch { dataController.preloadConfig() }

        // Validate controller
        validateControllerCache(aiuta = controller.aiuta)

        // Check operation history
        val countGeneratedOperation =
            controller.generatedOperationInteractor
                .countGeneratedOperation()
                .first()

        if (countGeneratedOperation > 0) {
            controller.updateActiveOperationWithFirstOrSetEmpty()
        }

        // Solve should show onboarding or not
        val isUserConsentObtainedFlowRaw =
            configuration.dataProvider?.isUserConsentObtainedFlow?.value
        val shouldShowOnboardingFromHost =
            isUserConsentObtainedFlowRaw != null && isUserConsentObtainedFlowRaw == false
        val shouldShowOnboarding =
            controller.onboardingInteractor.shouldShowOnboarding() || shouldShowOnboardingFromHost

        if (shouldShowOnboarding) {
            val firstOnboardingScreen =
                if (configuration.toggles.isPreOnboardingAvailable) {
                    NavigationScreen.Preonboarding
                } else {
                    NavigationScreen.Onboarding
                }

            navigateTo(firstOnboardingScreen)
        } else {
            navigateTo(NavigationScreen.ImageSelector)
        }
    }

    Box(modifier = modifier)
}
