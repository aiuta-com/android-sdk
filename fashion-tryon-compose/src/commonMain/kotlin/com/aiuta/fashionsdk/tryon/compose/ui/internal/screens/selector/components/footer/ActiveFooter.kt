package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.components.footer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.StartTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.shadow.dropShadow
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButton
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonSizes
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.AiutaImage
import com.aiuta.fashionsdk.tryon.compose.uikit.utils.clickableUnindicated

@Composable
internal fun ActiveFooter(modifier: Modifier = Modifier) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current

    val tryOnFeature = strictProvideFeature<AiutaTryOnFeature>()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))

        Column(
            modifier =
            Modifier
                .dropShadow(
                    shape = theme.bottomSheet.shapes.bottomSheetShape,
                    color = theme.color.primary.copy(alpha = 0.04f),
                    blur = 15.dp,
                    offsetY = (-10).dp,
                )
                .background(
                    color = theme.color.background,
                    shape = theme.bottomSheet.shapes.bottomSheetShape,
                )
                .padding(horizontal = 16.dp),
        ) {
            Spacer(Modifier.height(16.dp))

            SKUBlock(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
            )

            Spacer(Modifier.height(24.dp))

            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = tryOnFeature.strings.tryOnButtonTryOn,
                style = tryOnFeature.styles.tryOnButtonGradient?.let { tryOnButtonGradient ->
                    FashionButtonStyles.gradientColors(
                        contentColor = theme.color.onDark,
                        gradientBackground = Brush.horizontalGradient(tryOnButtonGradient),
                    )
                } ?: FashionButtonStyles.primaryStyle(theme),
                size = FashionButtonSizes.lSize(),
                icon = tryOnFeature.icons.magic20,
                onClick = {
                    controller.startGeneration(
                        aiutaConfiguration = aiutaConfiguration,
                        coilContext = coilContext,
                        dialogController = dialogController,
                        tryOnFeatureStrings = tryOnFeature.strings,
                        origin = StartTryOnEvent.TryOnOrigin.TRY_ON_BUTTON,
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

    val tryOnFeature = strictProvideFeature<AiutaTryOnFeature>()

    val activeSKUItem = controller.activeSKUItem.value
    val sharedCorner = RoundedCornerShape(size = 8.dp)

    Row(
        modifier = modifier.clickableUnindicated {
            controller.bottomSheetNavigator.show(
                NavigationBottomSheetScreen.SKUInfo(
                    primaryButtonState = NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState.ADD_TO_CART,
                    originPageId = AiutaAnalyticPageId.IMAGE_PICKER,
                    skuItem = activeSKUItem,
                ),
            )
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AiutaImage(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(0.7f)
                .border(
                    width = 1.dp,
                    color = theme.color.border,
                    shape = sharedCorner,
                )
                .clip(sharedCorner),
            imageUrl = activeSKUItem.imageUrls.firstOrNull(),
            shape = sharedCorner,
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
                style = theme.productBar.typography.brand,
                color = theme.color.primary,
                textAlign = TextAlign.Start,
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = activeSKUItem.description,
                style = theme.productBar.typography.product,
                color = theme.color.primary,
                textAlign = TextAlign.Start,
            )
        }

        Spacer(Modifier.width(8.dp))

        AiutaIcon(
            modifier =
            Modifier
                .size(16.dp)
                .align(Alignment.CenterVertically),
            icon = tryOnFeature.icons.arrow16,
            contentDescription = null,
            tint = theme.color.outline,
        )
    }
}
