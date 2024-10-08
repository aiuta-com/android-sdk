package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.icon.error36
import com.aiuta.fashionsdk.internal.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.hideErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

internal const val DEFAULT_SHOWING_DELAY = 4000L

@Composable
internal fun NavigationErrorCard(
    modifier: Modifier = Modifier,
    errorState: FashionTryOnErrorState,
) {
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val theme = LocalTheme.current

    Row(
        modifier =
            modifier
                .background(
                    color = theme.colors.error,
                    shape = theme.shapes.buttonM,
                )
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            painter = rememberAsyncImagePainter(theme.icons.error36),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Spacer(Modifier.width(16.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = errorState.message ?: stringResources.defaultErrorMessage,
            style = theme.typography.chips,
            color = theme.colors.onError,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.width(16.dp))

        FashionButton(
            text = stringResources.tryAgain,
            style =
                FashionButtonStyles.secondaryStyle(
                    backgroundColor = theme.colors.background,
                    contentColor = theme.colors.primary,
                    borderColor = Color.Transparent,
                ),
            size = FashionButtonSizes.mSize(),
            onClick = {
                controller.startGeneration(
                    aiutaConfiguration = aiutaConfiguration,
                    origin = StartUITryOn.Origin.TRY_ON_BUTTON,
                )
                controller.hideErrorState()
            },
        )
    }
}
