package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUMetaInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.SKUInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.progress.LoadingProgress
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

@Composable
internal fun NavigationSKUItem(
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit,
) {
    val controller = LocalController.current

    Row(
        modifier =
            modifier
                .padding(8.dp)
                .clickableUnindicated {
                    onItemClick()
                },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SKUImage(
            modifier =
                Modifier.size(
                    width = 40.dp,
                    height = 60.dp,
                ),
            skuMetaInfo = controller.skuMetaInfo,
        )

        Spacer(Modifier.width(10.dp))

        SKUInfo(
            modifier = Modifier.weight(1f),
            skuMetaInfo = controller.skuMetaInfo,
        )

        Spacer(Modifier.width(16.dp))

        Icon(
            modifier = Modifier.size(16.dp).rotate(180f),
            imageVector = ImageVector.vectorResource(id = FashionIcon.Arrow36),
            tint = FashionColor.DarkGray,
            contentDescription = null,
        )
    }
}

@Composable
private fun SKUImage(
    modifier: Modifier = Modifier,
    skuMetaInfo: () -> SKUMetaInfo,
) {
    SubcomposeAsyncImage(
        modifier =
            modifier
                .clip(RoundedCornerShape(8.dp))
                .background(
                    color = FashionColor.White,
                    shape = RoundedCornerShape(8.dp),
                )
                .border(
                    width = 1.dp,
                    shape = RoundedCornerShape(8.dp),
                    color = Color(0xFFD9D9D9),
                ),
        model =
            ImageRequest.Builder(LocalContext.current)
                .data(skuMetaInfo().imageUrls.firstOrNull())
                .crossfade(true)
                .build(),
        loading = {
            LoadingProgress(modifier = Modifier.fillMaxSize())
        },
        contentScale = ContentScale.Crop,
        contentDescription = null,
    )
}
