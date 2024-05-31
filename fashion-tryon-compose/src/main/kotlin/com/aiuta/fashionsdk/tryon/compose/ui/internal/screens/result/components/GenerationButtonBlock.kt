package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.internal.analytic.model.FinishSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToCart
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishList
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme

@Composable
internal fun GenerationButtonsBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val configuration = LocalAiutaConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Row(
        modifier = modifier.height(intrinsicSize = IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (configuration.isWishlistAvailable) {
            FashionButton(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                text = stringResources.addToWish,
                style = FashionButtonStyles.outlineStyle(theme),
                size = FashionButtonSizes.xlSize(),
                onClick = {
                    controller.clickAddToWishList(origin = FinishSession.Origin.RESULT_SCREEN)
                },
            )

            Spacer(Modifier.width(8.dp))
        }

        FashionButton(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            text = stringResources.addToCart,
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                controller.clickAddToCart(origin = FinishSession.Origin.RESULT_SCREEN)
            },
        )
    }
}
