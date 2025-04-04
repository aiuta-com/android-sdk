package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.AiutaLabel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.CenterAlignmentLine
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration.rememberScreenSize
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.createCenterAlignmentLine

@Composable
internal fun InactiveFooter(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val screenSize = rememberScreenSize()
    val horizontalPadding = screenSize.widthDp * 0.2f

    val generationStatus = controller.generationStatus
    val skuGenerationTransition =
        updateTransition(
            targetState = generationStatus.value,
            label = "skuGenerationTransition",
        )

    Column(
        modifier = modifier.padding(horizontal = horizontalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(20.dp))

        skuGenerationTransition.AnimatedVisibility(
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

    val lineIndexToCenter = 0
    var topTextPosition by remember { mutableFloatStateOf(0f) }
    var bottomTextPosition by remember { mutableFloatStateOf(0f) }

    Row(
        modifier = modifier,
    ) {
        AiutaIcon(
            modifier =
                Modifier
                    .size(16.dp)
                    .alignBy(CenterAlignmentLine)
                    .createCenterAlignmentLine(),
            icon = theme.icons.lock16,
            contentDescription = null,
            tint = theme.colors.secondary,
        )

        Text(
            modifier =
                Modifier
                    .wrapContentWidth()
                    .alignBy(CenterAlignmentLine)
                    .createCenterAlignmentLine(topTextPosition, bottomTextPosition),
            text = stringResources.imageSelectorProtectionPoint,
            style = theme.typography.description,
            color = theme.colors.secondary,
            textAlign = TextAlign.Center,
            onTextLayout = { textLayout: TextLayoutResult ->
                topTextPosition = textLayout.getLineTop(lineIndexToCenter)
                bottomTextPosition = textLayout.getLineBottom(lineIndexToCenter)
            },
        )
    }
}
