package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.content.empty.body

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.molecules.images.AiutaImage
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.images.AiutaImage
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.dropShadow

@Composable
internal fun ImageSelectorScreenEmptyBodyBlock(modifier: Modifier) {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    val controller = LocalController.current
    val configuration = LocalConfiguration.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val screenWidth = configuration.screenWidthDp.dp
    val imageBlockPadding = screenWidth * 0.15f

    Column(
        modifier =
            modifier
                .padding(horizontal = 26.dp)
                .background(
                    color = theme.colors.neutral,
                    shape = RoundedCornerShape(24.dp),
                )
                .padding(horizontal = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(60.dp))

        ImagesBlock(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(imageBlockPadding),
        )

        Spacer(Modifier.height(60.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResources.imageSelectorUploadTitle,
            style = theme.typography.titleM,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResources.imageSelectorUploadSubtitle,
            style = theme.typography.chips,
            color = theme.colors.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(32.dp))

        FashionButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResources.imageSelectorUploadButton,
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.lSize(),
            onClick = {
                controller.bottomSheetNavigator.show(
                    newSheetScreen =
                        NavigationBottomSheetScreen.ImagePicker(
                            originPageId = AiutaAnalyticPageId.IMAGE_PICKER,
                        ),
                )
            },
        )

        if (aiutaConfiguration.toggles.isTryonWithModelsAvailable) {
            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResources.imageSelectorOr,
                style = theme.typography.chips,
                color = theme.colors.primary,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(20.dp))

            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResources.imageSelectorSelectModelButton,
                style =
                    FashionButtonStyles.secondaryStyle(
                        backgroundColor = theme.colors.background,
                        contentColor = theme.colors.primary,
                        borderColor = Color.Transparent,
                    ),
                size = FashionButtonSizes.lSize(),
                onClick = {
                    // TODO Navigation to model selector
                },
            )

            Spacer(Modifier.height(32.dp))
        } else {
            Spacer(Modifier.weight(0.5f))
        }
    }
}

@Composable
private fun ImagesBlock(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current
    val density = LocalDensity.current

    val paddingPx = with(density) { (32.dp).toPx() }

    Box(
        modifier = modifier,
    ) {
        ImageContainer(
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        translationX = paddingPx
                        rotationZ = 10f
                    },
            image = theme.images.selectorEmptySmallImage2,
        )

        ImageContainer(
            modifier =
                Modifier
                    .align(Alignment.Center)
                    .graphicsLayer {
                        translationX = -paddingPx
                        rotationZ = -12f
                    },
            image = theme.images.selectorEmptySmallImage1,
        )
    }
}

@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    image: AiutaImage,
) {
    val theme = LocalTheme.current

    val sharedShape = RoundedCornerShape(16.dp)

    AiutaImage(
        modifier =
            modifier
                .fillMaxHeight()
                .aspectRatio(0.63f)
                .dropShadow(
                    shape = sharedShape,
                    color = theme.colors.primary.copy(alpha = 0.06f),
                    blur = (13.42).dp,
                    offsetY = (13.42).dp,
                )
                .clip(sharedShape)
                .border(
                    width = 4.dp,
                    color = theme.colors.onDark,
                    shape = sharedShape,
                ),
        image = image,
        contentDescription = null,
    )
}
