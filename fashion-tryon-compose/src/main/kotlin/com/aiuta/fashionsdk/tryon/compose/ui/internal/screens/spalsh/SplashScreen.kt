package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.spalsh

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.delay

private const val SPLASH_SCREEN_SHOWING_DELAY = 1000L

@Composable
internal fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateTo: (NavigationScreen) -> Unit,
) {
    val theme = LocalTheme.current

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
        Image(
            modifier = Modifier.fillMaxWidth(0.4f),
            painter = painterResource(theme.navLogo),
            colorFilter = ColorFilter.tint(color = theme.colors.navLogoColor),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
    }
}
