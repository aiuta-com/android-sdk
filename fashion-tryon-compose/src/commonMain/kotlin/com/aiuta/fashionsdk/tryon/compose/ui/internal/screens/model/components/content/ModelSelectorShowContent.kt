package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.toUrlImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.screen.model.ModelSelectorScreenState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateAutoTryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.utils.MODEL_IMAGE_BOTTOM_PADDING_COEF
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.utils.MODEL_IMAGE_HORIZONTAL_PADDING_COEF
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.configuration.rememberScreenSize
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.placeholderFadeConnecting

@Composable
internal fun ModelSelectorShowContent(
    modifier: Modifier = Modifier,
    state: ModelSelectorScreenState.Content,
) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val theme = LocalTheme.current

    val screenSize = rememberScreenSize()

    val imageHorizontalPadding = screenSize.widthDp * MODEL_IMAGE_HORIZONTAL_PADDING_COEF
    val bottomPadding = screenSize.heightDp * MODEL_IMAGE_BOTTOM_PADDING_COEF

    val activeCategory = remember { mutableStateOf(state.categories.firstOrNull()) }
    val activeImageModel = remember { mutableStateOf(activeCategory.value?.models?.firstOrNull()) }

    val imageModel =
        rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(coilContext)
                .data(activeImageModel.value?.url)
                .crossfade(true)
                .build(),
        )
    val imageModelState = imageModel.state.collectAsState()
    val isMainImageShimmerVisible =
        remember {
            derivedStateOf { imageModelState.value !is AsyncImagePainter.State.Success }
        }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier =
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = imageHorizontalPadding)
                .clip(RoundedCornerShape(24.dp))
                .placeholderFadeConnecting(
                    shapeDp = 24.dp,
                    visible = isMainImageShimmerVisible.value,
                ),
            painter = imageModel,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )

        Spacer(Modifier.height(26.dp))

        ModelsCategoriesBlock(
            state = state,
            activeCategory = activeCategory,
        )

        Spacer(Modifier.height(30.dp))

        ModelsListBlock(
            modifier = Modifier.fillMaxWidth(),
            activeCategory = activeCategory,
            activeImageModel = activeImageModel,
        )

        Spacer(Modifier.height(20.dp))

        Column(
            modifier = Modifier.height(bottomPadding),
        ) {
            Spacer(Modifier.weight(1f))

            FashionButton(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = stringResources.tryOn,
                style =
                if (theme.gradients.tryOnButtonBackground.isNotEmpty()) {
                    FashionButtonStyles.gradientColors(
                        contentColor = theme.colors.onDark,
                        gradientBackground =
                        Brush.horizontalGradient(
                            theme.gradients.tryOnButtonBackground,
                        ),
                    )
                } else {
                    FashionButtonStyles.primaryStyle(theme)
                },
                size = FashionButtonSizes.lSize(),
                icon = theme.icons.magic20,
                onClick = {
                    activeImageModel.value?.let { model ->
                        // Save model
                        controller.lastSavedImages.value =
                            LastSavedImages.UrlSource.PregeneratedModels(
                                urlImages = listOf(model.toUrlImage()),
                            )
                        // Activate try on
                        controller.activateAutoTryOn()

                        // Go back to picker
                        controller.navigateTo(NavigationScreen.ImageSelector)
                    }
                },
            )

            Spacer(Modifier.weight(1f))
        }
    }
}
