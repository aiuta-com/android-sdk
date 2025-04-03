package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.disclaimer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

@Composable
internal fun ColumnScope.FitDisclaimerSheet() {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val fitDisclaimerFeature = strictProvideFeature<AiutaTryOnFitDisclaimerFeature>()

    val sharedModifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)

    SheetDivider()

    Spacer(Modifier.height(30.dp))

    Text(
        modifier = sharedModifier,
        text = fitDisclaimerFeature.strings.tryOnFitDescription,
        style = theme.typography.navbar,
        color = theme.colors.primary,
        textAlign = TextAlign.Start,
    )

    Spacer(Modifier.height(32.dp))

    FashionButton(
        modifier = sharedModifier,
        text = fitDisclaimerFeature.strings.tryOnFitButtonClose,
        style = FashionButtonStyles.secondaryStyle(theme),
        size = FashionButtonSizes.lSize(),
        onClick = {
            controller.bottomSheetNavigator.hide()
        },
    )
}
