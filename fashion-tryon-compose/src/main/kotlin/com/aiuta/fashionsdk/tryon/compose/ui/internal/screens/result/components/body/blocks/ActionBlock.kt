package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.ShareManager
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishListActiveSKU
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendShareGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.LikeButton

@Composable
internal fun ActionBlock(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val context = LocalContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current

    val activeSKUItem = controller.activeSKUItem.value
    val shareManager = remember { ShareManager(context) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        IconButton(
            icon = theme.icons.share24,
            onClick = {
                val imageUrls = listOfNotNull(imageUrl)
                // Analytic
                controller.sendShareGeneratedImageEvent(
                    origin = ShareGeneratedImage.Origin.RESULT_SCREEN,
                    count = imageUrls.size,
                    additionalShareInfo = activeSKUItem.additionalShareInfo,
                )
                controller.sendResultEvent(
                    event = AiutaAnalyticsResultsEventType.RESULT_SHARED,
                    productId = activeSKUItem.skuId,
                )

                shareManager.share(
                    content = activeSKUItem.additionalShareInfo,
                    imageUrls = imageUrls,
                    watermark = theme.watermark,
                    origin = ShareGeneratedImage.Origin.RESULT_SCREEN,
                )
            },
        )

        Spacer(Modifier.height(10.dp))

        LikeButton(
            modifier = Modifier.size(38.dp),
            isLiked = activeSKUItem.inWishlist,
            iconSize = 24.dp,
            onClick = {
                controller.clickAddToWishListActiveSKU(skuId = activeSKUItem.skuId)
            },
        )
    }
}
