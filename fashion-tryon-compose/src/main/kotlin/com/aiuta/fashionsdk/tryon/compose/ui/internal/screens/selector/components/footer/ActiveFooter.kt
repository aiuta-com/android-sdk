package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.dropShadow

@Composable
internal fun ActiveFooter(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        Column(
            modifier =
                Modifier
                    .dropShadow(
                        shape = theme.shapes.bottomSheet,
                        color = theme.colors.primary.copy(alpha = 0.04f),
                        blur = 15.dp,
                        offsetY = (-10).dp,
                    )
                    .background(
                        color = theme.colors.background,
                        shape = theme.shapes.bottomSheet,
                    )
                    .padding(horizontal = 16.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars),
        ) {
            Spacer(Modifier.height(16.dp))

            SKUBlock(
                modifier =
                    Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
            )

            Spacer(Modifier.height(24.dp))

            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResources.tryOn,
                style = FashionButtonStyles.primaryStyle(theme),
                size = FashionButtonSizes.lSize(),
                icon = theme.icons.magic16,
                onClick = {
                    controller.startGeneration(
                        aiutaConfiguration = aiutaConfiguration,
                        context = context,
                        dialogController = dialogController,
                        stringResources = stringResources,
                    )
                },
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun SKUBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val activeSKUItem = controller.activeSKUItem.value

    val sharedCorner = RoundedCornerShape(size = 8.dp)

    Row(
        modifier =
            modifier
                .clickableUnindicated {
                    controller.bottomSheetNavigator.show(
                        NavigationBottomSheetScreen.SKUInfo(
                            primaryButtonState = NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState.ADD_TO_CART,
                            skuItem = activeSKUItem,
                        ),
                    )
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .aspectRatio(0.7f)
                    .border(
                        width = 1.dp,
                        color = theme.colors.neutral2,
                        shape = sharedCorner,
                    )
                    .clip(sharedCorner),
            painter = rememberAsyncImagePainter(activeSKUItem.imageUrls.firstOrNull()),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = activeSKUItem.store,
                style = theme.typography.brandName,
                color = theme.colors.primary,
                textAlign = TextAlign.Start,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = activeSKUItem.description,
                style = theme.typography.productName,
                color = theme.colors.primary,
                textAlign = TextAlign.Start,
            )
        }

        Spacer(Modifier.width(8.dp))

        AiutaIcon(
            modifier = Modifier.size(16.dp).align(Alignment.CenterVertically),
            icon = theme.icons.arrow16,
            contentDescription = null,
            tint = theme.colors.neutral3,
        )
    }
}
