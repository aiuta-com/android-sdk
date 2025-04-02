package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.components.appbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components.appbar.AppBarIcon
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

@Composable
internal fun ModelSelectorAppBar(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val predefinedModelFeature = strictProvideFeature<AiutaImageSelectorPredefinedModel>()

    AppBar(
        modifier = modifier,
        navigationIcon = {
            AppBarIcon(
                modifier = Modifier.align(Alignment.CenterStart),
                icon = theme.icons.back24,
                color = theme.colors.primary,
                onClick = controller::navigateBack,
            )
        },
        title = {
            Text(
                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                text = predefinedModelFeature.strings.predefinedModelPageTitle,
                style = theme.typography.navbar,
                color = theme.colors.primary,
                textAlign = TextAlign.Center,
            )
        },
    )
}
