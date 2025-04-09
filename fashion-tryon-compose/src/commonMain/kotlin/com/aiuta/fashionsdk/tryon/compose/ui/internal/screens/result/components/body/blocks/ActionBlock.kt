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
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsResultsEventType
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.AiutaShareFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.wishlist.AiutaWishlistFeature
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.rememberShareManagerV2
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToWishListActiveSKU
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic.sendResultEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.IconButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.LikeButton
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.dataprovider.safeInvoke
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.provideFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.wishlist.inWishlistListener
import com.aiuta.fashionsdk.tryon.compose.uikit.resources.painter.painterResource
import kotlinx.coroutines.launch

@Composable
internal fun ActionBlock(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val controller = LocalController.current

    val shareFeature = provideFeature<AiutaShareFeature>()
    val wishlistFeature = provideFeature<AiutaWishlistFeature>()

    val activeSKUItem = controller.activeProductItem.value
    val shareManager = rememberShareManagerV2()
    val scope = rememberCoroutineScope()

    val watermarkPainter = shareFeature?.watermark?.images?.logo?.let { logo ->
        painterResource(logo)
    }

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
                        val skuIds = listOf(controller.activeProductItem.value.id)
                        val shareText = shareFeature.dataProvider?.requestShareTextAction?.safeInvoke(skuIds)

                        // Analytic
                        controller.sendResultEvent(
                            event = AiutaAnalyticsResultsEventType.RESULT_SHARED,
                            pageId = AiutaAnalyticPageId.RESULTS,
                            productId = activeSKUItem.id,
                        )

                        shareManager.shareImages(
                            content = shareText?.getOrNull(),
                            pageId = AiutaAnalyticPageId.RESULTS,
                            productId = activeSKUItem.id,
                            imageUrls = imageUrls,
                            watermark = watermarkPainter,
                        )
                    }
                },
            )

            Spacer(Modifier.height(10.dp))
        }

        wishlistFeature?.let {
            val inWishlist = wishlistFeature.inWishlistListener()

            LikeButton(
                modifier = Modifier.size(38.dp),
                isLiked = inWishlist.value,
                iconSize = 24.dp,
                wishlistFeature = wishlistFeature,
                onClick = { currentState ->
                    controller.clickAddToWishListActiveSKU(
                        pageId = AiutaAnalyticPageId.RESULTS,
                        updatedWishlistState = !currentState,
                        dataProvider = wishlistFeature.dataProvider,
                        productId = activeSKUItem.id,
                    )
                },
            )
        }
    }
}
