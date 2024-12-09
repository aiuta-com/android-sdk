package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsFeedbackEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsFeedbackEventType
import com.aiuta.fashionsdk.internal.analytic.model.DislikeGenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.GenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.LikeGenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.SelectMoreToTryOn
import com.aiuta.fashionsdk.internal.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.internal.analytic.model.ViewMoreToTryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

private var lastScrolledImageIndex = 0

internal fun FashionTryOnController.sendViewGeneratedImageEvent(
    newIndex: Int,
    type: ViewGeneratedImage.NavigationType,
) {
    if (lastScrolledImageIndex != newIndex) {
        lastScrolledImageIndex = newIndex
        val activeSKUItem = activeSKUItem.value

        analytic.sendEvent(
            event =
                ViewGeneratedImage(
                    imageNumber = newIndex.toString(),
                    navigationType = type.value,
                    skuId = activeSKUItem.skuId,
                    skuCatalogName = activeSKUItem.catalogName,
                ),
        )
    }
}

internal fun FashionTryOnController.sendViewMoreToTryOnEvent() {
    analytic.sendEvent(ViewMoreToTryOn())
}

internal fun FashionTryOnController.sendSelectMoreToTryOnEvent(skuItem: SKUItem) {
    analytic.sendEvent(
        event =
            SelectMoreToTryOn(
                skuId = skuItem.skuId,
                skuCatalogName = skuItem.catalogName,
            ),
    )
}

internal fun FashionTryOnController.sendGenerationFeedback(
    generationIndex: Int,
    optionIndex: Int? = null,
    feedback: String? = null,
) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(
        event =
            GenerationFeedback(
                skuId = activeSKUItem.skuId,
                skuCatalogName = activeSKUItem.catalogName,
                generatedPhotoPosition = generationIndex.toString(),
                feedback = feedback,
            ),
    )
    analytic.sendEvent(
        event =
            AiutaAnalyticsFeedbackEvent(
                event = AiutaAnalyticsFeedbackEventType.NEGATIVE,
                negativeFeedbackOptionIndex = optionIndex,
                negativeFeedbackText = feedback,
                pageId = AiutaAnalyticPageId.RESULTS,
                productId = activeSKUItem.skuId,
            ),
    )
}

internal fun FashionTryOnController.sendLikeGenerationFeedback(generationIndex: Int) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(
        event =
            LikeGenerationFeedback(
                skuId = activeSKUItem.skuId,
                skuCatalogName = activeSKUItem.catalogName,
                generatedPhotoPosition = generationIndex.toString(),
            ),
    )
    analytic.sendEvent(
        event =
            AiutaAnalyticsFeedbackEvent(
                event = AiutaAnalyticsFeedbackEventType.POSITIVE,
                pageId = AiutaAnalyticPageId.RESULTS,
                productId = activeSKUItem.skuId,
            ),
    )
}

internal fun FashionTryOnController.sendDislikeGenerationFeedback(generationIndex: Int) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(
        event =
            DislikeGenerationFeedback(
                skuId = activeSKUItem.skuId,
                skuCatalogName = activeSKUItem.catalogName,
                generatedPhotoPosition = generationIndex.toString(),
            ),
    )
}
