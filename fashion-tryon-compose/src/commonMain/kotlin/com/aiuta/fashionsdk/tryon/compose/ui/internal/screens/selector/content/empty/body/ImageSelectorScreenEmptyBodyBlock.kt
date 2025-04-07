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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.model.AiutaImageSelectorPredefinedModelFeature
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration.rememberScreenSize
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.shadow.dropShadow
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButton
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonSizes
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.AiutaImage

@Composable
internal fun ImageSelectorScreenEmptyBodyBlock(modifier: Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val screenSize = rememberScreenSize()
    val imageBlockPadding = screenSize.widthDp * 0.15f

    val imageSelectorFeature = strictProvideFeature<AiutaImageSelectorFeature>()
    val predefinedModelFeature = provideFeature<AiutaImageSelectorPredefinedModelFeature>()

    Column(
        modifier =
        modifier
            .padding(horizontal = 26.dp)
            .background(
                color = theme.color.neutral,
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
            text = imageSelectorFeature.strings.imageSelectorTitleEmpty,
            style = theme.label.typography.titleM,
            color = theme.color.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(16.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = imageSelectorFeature.strings.imageSelectorDescriptionEmpty,
            style = theme.label.typography.subtle,
            color = theme.color.primary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(32.dp))

        FashionButton(
            modifier = Modifier.fillMaxWidth(),
            text = imageSelectorFeature.strings.imageSelectorButtonUploadImage,
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

        predefinedModelFeature?.let {
            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = predefinedModelFeature.strings.predefinedModelOr,
                style = theme.label.typography.subtle,
                color = theme.color.primary,
                textAlign = TextAlign.Center,
            )

            Spacer(Modifier.height(20.dp))

            FashionButton(
                modifier = Modifier.fillMaxWidth(),
                text = predefinedModelFeature.strings.predefinedModelPageTitle,
                style =
                FashionButtonStyles.secondaryStyle(
                    backgroundColor = theme.color.background,
                    contentColor = theme.color.primary,
                    borderColor = Color.Transparent,
                ),
                size = FashionButtonSizes.lSize(),
                onClick = {
                    controller.navigateTo(NavigationScreen.ModelSelector)
                },
            )

            Spacer(Modifier.height(32.dp))
        } ?: Spacer(Modifier.weight(0.5f))
    }
}

@Composable
private fun ImagesBlock(modifier: Modifier = Modifier) {
    val density = LocalDensity.current

    val imageSelectorFeature = strictProvideFeature<AiutaImageSelectorFeature>()
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
            image = imageSelectorFeature.images.examples.getOrNull(1),
        )

        ImageContainer(
            modifier =
            Modifier
                .align(Alignment.Center)
                .graphicsLayer {
                    translationX = -paddingPx
                    rotationZ = -12f
                },
            image = imageSelectorFeature.images.examples.getOrNull(0),
        )
    }
}

@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    image: AiutaDrawableResource?,
) {
    if (image == null) return

    val theme = LocalTheme.current

    val sharedShape = RoundedCornerShape(16.dp)

    AiutaImage(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(0.63f)
            .dropShadow(
                shape = sharedShape,
                color = theme.color.primary.copy(alpha = 0.06f),
                blur = (13.42).dp,
                offsetY = (13.42).dp,
            )
            .clip(sharedShape)
            .border(
                width = 4.dp,
                color = theme.color.onDark,
                shape = sharedShape,
            ),
        image = image,
        contentDescription = null,
    )
}
