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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources

@Composable
internal fun ModelSelectorEmptyModelsErrorContent(modifier: Modifier) {
    val configuration = LocalConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val screenWidth = configuration.screenWidthDp.dp
    val textHorizontalPadding = screenWidth * 0.26f

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
            text = stringResources.modelSelectorErrorEmptyModelsList,
            style = theme.typography.regular,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )
    }
}
