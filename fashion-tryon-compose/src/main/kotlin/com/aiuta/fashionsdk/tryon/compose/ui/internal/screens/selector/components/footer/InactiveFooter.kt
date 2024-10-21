package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.AiutaLabel

@Composable
internal fun InactiveFooter(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val configuration = LocalConfiguration.current
    val horizontalPadding = configuration.screenWidthDp.dp * 0.25f

    val generationStatus = controller.generationStatus
    val skuGenerationTransition =
        updateTransition(
            targetState = generationStatus.value,
            label = "skuGenerationTransition",
        )

    Column(
        modifier =
            modifier
                .padding(horizontal = horizontalPadding)
                .windowInsetsPadding(WindowInsets.navigationBars),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(20.dp))

        skuGenerationTransition.AnimatedVisibility(
            modifier = Modifier.fillMaxSize(),
            visible = { it != SKUGenerationUIStatus.LOADING },
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            ProtectionPoint(modifier = Modifier.fillMaxWidth())
        }

        Spacer(Modifier.weight(1f))

        AiutaLabel()

        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun ProtectionPoint(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        AiutaIcon(
            modifier = Modifier.size(16.dp),
            icon = theme.icons.lock16,
            contentDescription = null,
            tint = theme.colors.secondary,
        )

        Spacer(Modifier.width(4.dp))

        Text(
            modifier = modifier,
            text = stringResources.imageSelectorProtectionPoint,
            style = theme.typography.description,
            color = theme.colors.secondary,
            textAlign = TextAlign.Center,
        )
    }
}
