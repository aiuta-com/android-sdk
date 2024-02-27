package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.delay

private const val SPLASH_SCREEN_SHOWING_DELAY = 1000L

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    LaunchedEffect(Unit) {
        // Wait for showing splash screen
        delay(SPLASH_SCREEN_SHOWING_DELAY)

        // Navigate to image selector then
        navigateTo(NavigationScreen.IMAGE_SELECTOR)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth(0.5f),
            imageVector = ImageVector.vectorResource(id = FashionIcon.MainLogo),
            tint = FashionColor.ElectricBlue,
            contentDescription = null,
        )
    }
}
