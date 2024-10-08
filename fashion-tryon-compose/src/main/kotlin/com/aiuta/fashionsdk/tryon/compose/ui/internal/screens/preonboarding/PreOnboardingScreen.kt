package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.preonboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickClose
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendStartOnBoardingEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon

@Composable
internal fun PreOnboardingScreen(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    sendStartOnBoardingEvent()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.preonboarding_background),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        AppBar(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            actions = {
                AppBarIcon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    painter = rememberAsyncImagePainter(theme.icons.close24.resource),
                    color = Color.White,
                    onClick = {
                        controller.clickClose(FinishSession.Origin.PREONBOARDING_SCREEN)
                    },
                )
            },
        )

        PreOnboardingForeground()
    }
}

@Composable
private fun PreOnboardingForeground(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(82.dp),
            painter = rememberAsyncImagePainter(theme.icons.welcomeScreen82.resource),
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResources.preOnboardingTitle,
            style = theme.typography.titleXL,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResources.preOnboardingSubtitle,
            style = theme.typography.welcomeText,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(36.dp))

        StartButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                controller.navigateTo(NavigationScreen.ONBOARDING)
            },
        )
    }
}

@Composable
private fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Box(
        modifier =
            modifier
                .clip(RoundedCornerShape(4.dp))
                .background(theme.colors.background)
                .clickableUnindicated { onClick() }
                .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResources.preOnboardingButton,
            style = theme.typography.button,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )
    }
}
