package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.components.content

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import coil3.compose.AsyncImagePainter
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config.features.TryOnModelsCategoryUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.model.components.list.CentredModelsHorizontalPager
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.calculateCurrentOffsetForPage
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.placeholderFadeConnecting
import kotlin.math.absoluteValue

@Composable
internal fun ModelsListBlock(
    modifier: Modifier = Modifier,
    activeCategory: State<TryOnModelsCategoryUiModel?>,
    activeImageModel: MutableState<TryOnModelsCategoryUiModel.TryOnModelUiModel?>,
) {
    val coilContext = LocalPlatformContext.current
    val activeCategoryTransition = updateTransition(activeCategory.value)

    activeCategoryTransition.AnimatedContent(
        modifier = modifier,
        contentKey = { it?.category },
    ) { category ->
        category?.let {
            val horizontalPager = rememberPagerState { category.models.size }

            LaunchedEffect(horizontalPager.settledPage) {
                activeImageModel.value =
                    activeCategory.value
                        ?.models
                        ?.getOrNull(horizontalPager.settledPage)
            }

            CentredModelsHorizontalPager(
                modifier = modifier.height(124.dp),
                state = horizontalPager,
            ) { index ->
                val imageModel =
                    rememberAsyncImagePainter(
                        model =
                            ImageRequest.Builder(coilContext)
                                .data(category.models.getOrNull(index)?.url)
                                .crossfade(true)
                                .build(),
                    )
                val imageModelState = imageModel.state.collectAsState()

                val isShimmerVisible =
                    remember {
                        derivedStateOf { imageModelState.value !is AsyncImagePainter.State.Success }
                    }

                val pageOffset =
                    remember {
                        derivedStateOf {
                            1 -
                                horizontalPager.calculateCurrentOffsetForPage(
                                    index,
                                ).absoluteValue.coerceIn(
                                    0f,
                                    1f,
                                )
                        }
                    }

                val itemHeight =
                    remember {
                        derivedStateOf {
                            lerp(
                                105.dp,
                                124.dp,
                                pageOffset.value,
                            )
                        }
                    }

                val itemWidth =
                    remember {
                        derivedStateOf {
                            lerp(
                                66.dp,
                                76.dp,
                                pageOffset.value,
                            )
                        }
                    }

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    Image(
                        modifier =
                            Modifier
                                .width(itemWidth.value)
                                .height(itemHeight.value)
                                .clip(RoundedCornerShape(8.dp))
                                .placeholderFadeConnecting(
                                    shapeDp = 8.dp,
                                    visible = isShimmerVisible.value,
                                ),
                        painter = imageModel,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
    }
}
