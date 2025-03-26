package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishListGenerateMoreItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.SKUInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.ErrorProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.LikeButton

internal fun LazyGridScope.generateMoreListBlock(skuItem: SKUItem) {
    skuItem.generateMoreSKU?.let { skus ->
        itemsIndexed(
            items = skus,
            key = { _, sku -> "GENERATE_MORE_ITEM_KEY_${sku.uniqueGeneratedId}" },
            contentType = { _, _ -> "GENERATE_MORE_ITEM_TYPE" },
        ) { index, skuItem ->
            GenerationMoreItem(
                modifier =
                    Modifier
                        .padding(vertical = 4.dp)
                        .padding(
                            start = if (index % 2 == 0) 16.dp else 4.dp,
                            end = if (index % 2 == 0) 4.dp else 16.dp,
                        )
                        .aspectRatio(0.6f)
                        .fillMaxWidth(),
                skuItem = skuItem,
            )
        }
    }
}

@Composable
private fun GenerationMoreItem(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current

    val sharedCornerShape = RoundedCornerShape(20.dp)

    Column(
        modifier =
            modifier
                .background(
                    color = theme.colors.background,
                    shape = sharedCornerShape,
                )
                .border(
                    width = 1.dp,
                    color = theme.colors.neutral2,
                    shape = sharedCornerShape,
                )
                .padding(8.dp)
                .clickableUnindicated {
                    controller.bottomSheetNavigator.show(
                        newSheetScreen =
                            NavigationBottomSheetScreen.SKUInfo(
                                primaryButtonState = NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState.TRY_ON,
                                originPageId = AiutaAnalyticPageId.RESULTS,
                                skuItem = skuItem,
                            ),
                    )
                },
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier =
                Modifier
                    .aspectRatio(0.81f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp)),
        ) {
            SubcomposeAsyncImage(
                modifier =
                    Modifier
                        .clipToBounds()
                        .fillMaxSize(),
                model =
                    ImageRequest.Builder(coilContext)
                        .data(skuItem.imageUrls.firstOrNull())
                        .crossfade(true)
                        .build(),
                loading = { LoadingProgress(modifier = Modifier.fillMaxSize()) },
                error = { ErrorProgress(modifier = Modifier.fillMaxSize()) },
                contentScale = ContentScale.Crop,
                contentDescription = null,
            )

            TryOnLabel(
                modifier =
                    Modifier
                        .align(Alignment.BottomEnd)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 6.dp,
                        ),
            )

            LikeButton(
                modifier =
                    Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                        .size(28.dp),
                isLiked = skuItem.inWishlist,
                iconSize = 16.dp,
                onClick = {
                    controller.clickAddToWishListGenerateMoreItem(skuItem)
                },
            )
        }

        Spacer(Modifier.height(16.dp))

        SKUInfo(
            modifier = Modifier.fillMaxWidth(),
            skuItem = skuItem,
        )
    }
}
