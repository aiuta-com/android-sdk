package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.analytic.model.FinishSession
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToCart
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishList
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@Composable
internal fun GenerationButtonsBlock(modifier: Modifier = Modifier) {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current
    val theme = LocalTheme.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        FashionButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.add_to_wish),
            style = FashionButtonStyles.outlineStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                controller.clickAddToWishList(origin = FinishSession.Origin.RESULT_SCREEN)
            },
        )

        Spacer(Modifier.width(8.dp))

        FashionButton(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.add_to_cart),
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                controller.clickAddToCart(origin = FinishSession.Origin.RESULT_SCREEN)
            },
        )
    }
}
