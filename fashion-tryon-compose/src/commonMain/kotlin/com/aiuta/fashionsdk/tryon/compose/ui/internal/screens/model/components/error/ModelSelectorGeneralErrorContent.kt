package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.components.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration.rememberScreenSize

@Composable
internal fun ModelSelectorGeneralErrorContent(
    modifier: Modifier,
    onRetry: () -> Unit,
) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val screenSize = rememberScreenSize()
    val textHorizontalPadding = screenSize.widthDp * 0.26f

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AiutaIcon(
            modifier = Modifier.size(36.dp),
            icon = theme.icons.error36,
            tint = theme.colors.primary,
            contentDescription = null,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = textHorizontalPadding),
            text = stringResources.defaultErrorMessage,
            style = theme.typography.regular,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(8.dp))

        FashionButton(
            text = stringResources.tryAgain,
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.mSize(),
            onClick = onRetry,
        )
    }
}
