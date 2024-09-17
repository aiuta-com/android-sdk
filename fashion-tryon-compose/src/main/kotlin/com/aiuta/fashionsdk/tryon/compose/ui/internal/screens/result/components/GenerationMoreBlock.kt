package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.DiscountBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.solveGeneralPriceColor
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.solveGeneralPriceDecoration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendSelectMoreToTryOnEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerateResultState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.hideInterface
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.showInterface

private const val FULL_SIZE_SPAN = 2

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun GenerationMoreBlock(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val generateMoreSKU = controller.activeSKUItem.value.generateMoreSKU.orEmpty()
    val sharedModifier = Modifier.fillMaxWidth().height(300.dp)

    with(generationResultController.verticalSwipeState) {
        LaunchedEffect(currentValue) {
            if (currentValue == GenerateResultState.SHOW_GENERATIONS) {
                // Show all interface
                generationResultController.showInterface()
            } else {
                // Hide all interface
                generationResultController.hideInterface()
            }
        }
    }

    LazyVerticalGrid(
        modifier = modifier.padding(horizontal = 16.dp),
        columns = GridCells.Fixed(FULL_SIZE_SPAN),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(
            key = "TITLE_ITEM_KEY",
            span = { GridItemSpan(FULL_SIZE_SPAN) },
        ) {
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResources.generationResultMoreTitle,
                style = MaterialTheme.typography.h5,
                color = theme.colors.primary,
                fontWeight = FontWeight.Bold,
            )

            Spacer(Modifier.height(16.dp))
        }

        items(
            items = generateMoreSKU,
            key = { it.uniqueGeneratedId },
            contentType = { it },
        ) { skuItem ->
            GenerationMoreBlockItem(
                modifier = sharedModifier,
                skuItem = skuItem,
            )
        }
    }
}

@Composable
private fun GenerationMoreBlockItem(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val context = LocalContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val sharedCornerShape = RoundedCornerShape(16.dp)

    Column(
        modifier =
            modifier
                .background(
                    color = theme.colors.background,
                    shape = sharedCornerShape,
                )
                .border(
                    width = 1.dp,
                    color = theme.colors.neutral,
                    shape = sharedCornerShape,
                )
                .padding(8.dp)
                .clickableUnindicated {
                    controller.sendSelectMoreToTryOnEvent(skuItem)
                    controller.bottomSheetNavigator.show(
                        newSheetScreen =
                            NavigationBottomSheetScreen.SKUInfo(
                                primaryButtonState = NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState.TRY_ON,
                                skuItem = skuItem,
                            ),
                    )
                },
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(8.dp)),
        ) {
            SubcomposeAsyncImage(
                modifier =
                    Modifier
                        .clipToBounds()
                        .fillMaxSize(),
                model =
                    ImageRequest.Builder(context)
                        .data(skuItem.imageUrls.firstOrNull())
                        .crossfade(true)
                        .build(),
                loading = {
                    LoadingProgress(modifier = Modifier.fillMaxSize())
                },
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            if (skuItem.localizedOldPrice?.isNotBlank() == true && skuItem.localizedDiscount?.isNotBlank() == true) {
                DiscountBlock(
                    modifier =
                        Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp),
                    localizedDiscount = skuItem.localizedDiscount,
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Text(
            text = skuItem.store,
            style = MaterialTheme.typography.body1,
            color = theme.colors.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = skuItem.description,
            style = MaterialTheme.typography.body2,
            color = theme.colors.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (skuItem.localizedPrice.isNotBlank()) {
                Text(
                    text = skuItem.localizedPrice,
                    style =
                        MaterialTheme.typography.body1.copy(
                            textDecoration = solveGeneralPriceDecoration(skuItem),
                        ),
                    color = solveGeneralPriceColor(skuItem),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (skuItem.localizedOldPrice?.isNotBlank() == true) {
                Spacer(Modifier.width(4.dp))

                Text(
                    text = skuItem.localizedOldPrice,
                    style = MaterialTheme.typography.body1,
                    color = theme.colors.accent,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
