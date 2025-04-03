package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.blocks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.LocalPlatformContext
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.rememberShareManagerV2
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishListActiveSKU
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.LikeButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import kotlinx.coroutines.launch

@Composable
internal fun ActionBlock(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val coilContext = LocalPlatformContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current

    val shareFeature = provideFeature<AiutaShareFeature>()

    val activeSKUItem = controller.activeSKUItem.value
    val shareManager = rememberShareManagerV2()
    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        shareFeature?.let {
            IconButton(
                icon = shareFeature.icons.share24,
                onClick = {
                    scope.launch {
                        val imageUrls = listOfNotNull(imageUrl)
                        // Analytic
                        controller.sendResultEvent(
                            event = AiutaAnalyticsResultsEventType.RESULT_SHARED,
                            pageId = AiutaAnalyticPageId.RESULTS,
                            productId = activeSKUItem.skuId,
                        )

                        shareManager.shareImages(
                            coilContext = coilContext,
                            content = activeSKUItem.additionalShareInfo,
                            productId = activeSKUItem.skuId,
                            pageId = AiutaAnalyticPageId.RESULTS,
                            imageUrls = imageUrls,
                            watermark = theme.watermark,
                        )
                    }
                },
            )

            Spacer(Modifier.height(10.dp))
        }

        LikeButton(
            modifier = Modifier.size(38.dp),
            isLiked = activeSKUItem.inWishlist,
            iconSize = 24.dp,
            onClick = {
                controller.clickAddToWishListActiveSKU(
                    pageId = AiutaAnalyticPageId.RESULTS,
                    skuId = activeSKUItem.skuId,
                )
            },
        )
    }
}
