package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.skuinfo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.tryon.compose.R
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.SKUInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.changeActiveSKU
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen.SKUInfo.PrimaryButtonState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider

@Composable
internal fun ColumnScope.SKUInfoSheet(skuInfo: NavigationBottomSheetScreen.SKUInfo) {
    val sharedHorizontalPadding = 16.dp

    SheetDivider()

    Spacer(Modifier.height(16.dp))

    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = sharedHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        itemsIndexed(
            items = skuInfo.skuItem.imageUrls,
            key = { index, _ -> index },
        ) { _, imageUrl ->
            ImageContainer(
                modifier =
                    Modifier.size(
                        width = 154.dp,
                        height = 202.dp,
                    ),
                imageUrl = imageUrl,
            )
        }
    }

    Spacer(Modifier.height(16.dp))

    SKUInfo(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = sharedHorizontalPadding),
        skuItem = skuInfo.skuItem,
    )

    Spacer(Modifier.height(24.dp))

    ButtonsContainer(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = sharedHorizontalPadding),
        skuInfo = skuInfo,
    )

    Spacer(Modifier.windowInsetsPadding(WindowInsets.navigationBars))
}

@Composable
private fun ImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    SubcomposeAsyncImage(
        modifier =
            modifier
                .clip(RoundedCornerShape(8.dp))
                .background(FashionColor.White),
        model =
            ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
        loading = {
            LoadingProgress(modifier = Modifier.fillMaxSize())
        },
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}

@Composable
private fun ButtonsContainer(
    modifier: Modifier = Modifier,
    skuInfo: NavigationBottomSheetScreen.SKUInfo,
) {
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
            onClick = controller.fashionTryOnListeners().addToWishlistClick,
        )

        Spacer(Modifier.width(8.dp))

        FashionButton(
            modifier = Modifier.weight(1f),
            text =
                stringResource(
                    if (skuInfo.primaryButtonState == PrimaryButtonState.ADD_TO_CART) {
                        R.string.add_to_cart
                    } else {
                        R.string.try_on
                    },
                ),
            iconRes = FashionIcon.Magic.takeIf { skuInfo.primaryButtonState == PrimaryButtonState.TRY_ON },
            style = FashionButtonStyles.primaryStyle(theme),
            size = FashionButtonSizes.xlSize(),
            onClick = {
                if (skuInfo.primaryButtonState == PrimaryButtonState.ADD_TO_CART) {
                    controller.fashionTryOnListeners().addToCartClick()
                } else {
                    controller.changeActiveSKU(skuInfo.skuItem)
                    controller.bottomSheetNavigator.hide()
                    controller.navigateBack()
                }
            },
        )
    }
}
