package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val theme = LocalTheme.current
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        // Check operation history
        val countGeneratedOperation = controller.generatedOperationInteractor.countGeneratedOperation()
        if (countGeneratedOperation > 0) {
            val lastOperation = controller.generatedOperationInteractor.getLastGeneratedOperation()
            controller.lastSavedPhotoUris.value = lastOperation.sourceImageUrls
        }

        // Solve should show onboarding or not
        val shouldShowOnboarding = controller.onboardingInteractor.shouldShowOnboarding()

        if (shouldShowOnboarding) {
            navigateTo(NavigationScreen.ONBOARDING)
        } else {
            navigateTo(NavigationScreen.IMAGE_SELECTOR)
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.4f),
            painter = painterResource(theme.navBarTheme.navLogo),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}
