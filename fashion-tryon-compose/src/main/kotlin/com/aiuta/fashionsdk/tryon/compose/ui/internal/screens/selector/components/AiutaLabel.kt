package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.providePoweredByUrl

@Composable
internal fun AiutaLabel(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val dataController = LocalAiutaTryOnDataController.current

    val poweredByUrl =
        remember {
            mutableStateOf<String?>(null)
        }

    LaunchedEffect(Unit) {
        poweredByUrl.value = dataController.providePoweredByUrl()
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = poweredByUrl.value != null,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        poweredByUrl.value?.let {
            AiutaLabelContent()
        }
    }
}

@Composable
private fun AiutaLabelContent(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Box(
        modifier =
            modifier
                .background(
                    shape = RoundedCornerShape(100.dp),
                    color = theme.colors.neutral,
                )
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResources.imageSelectorPoweredByAiuta,
            style = theme.typography.smallButton,
            color = theme.colors.primary,
        )
    }
}
