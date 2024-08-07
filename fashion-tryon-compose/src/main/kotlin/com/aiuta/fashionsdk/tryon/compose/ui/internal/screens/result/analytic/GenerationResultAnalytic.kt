package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.DislikeGenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.GenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.LikeGenerationFeedback
import com.aiuta.fashionsdk.internal.analytic.model.OpenResultsScreen
import com.aiuta.fashionsdk.internal.analytic.model.SelectMoreToTryOn
import com.aiuta.fashionsdk.internal.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.internal.analytic.model.ViewMoreToTryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.size
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToLoadingOperations

@Composable
internal fun sendOpenResultsScreenEvent() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current
    val activeSKUItem = controller.activeSKUItem.value

    val lastSavedPhotoUris = controller.lastSavedImages.value
    val loadingOperations = controller.subscribeToLoadingOperations().value

    LaunchedEffect(Unit) {
        analytic.sendEvent(OpenResultsScreen) {
            put(
                key = OpenResultsScreen.SKU_ID_PARAM,
                value = activeSKUItem.skuId,
            )
            put(
                key = OpenResultsScreen.SKU_CATALOG_NAME_PARAM,
                value = activeSKUItem.catalogName,
            )
            put(
                key = OpenResultsScreen.GENERATED_PHOTOS_PARAM,
                value = lastSavedPhotoUris.size.toString(),
            )
            put(
                key = OpenResultsScreen.PHOTOS_IN_PROGRESS_PARAM,
                value = loadingOperations.size.toString(),
            )
            put(
                key = OpenResultsScreen.MORE_TO_TRY_ON_PARAM,
                value = (activeSKUItem.generateMoreSKU?.size ?: 0).toString(),
            )
        }
    }
}

private var lastScrolledImageIndex = 0

internal fun FashionTryOnController.sendViewGeneratedImageEvent(
    newIndex: Int,
    type: ViewGeneratedImage.NavigationType,
) {
    if (lastScrolledImageIndex != newIndex) {
        lastScrolledImageIndex = newIndex
        val activeSKUItem = activeSKUItem.value

        analytic.sendEvent(ViewGeneratedImage) {
            put(
                key = ViewGeneratedImage.IMAGE_NUMBER_PARAM,
                value = newIndex.toString(),
            )
            put(
                key = ViewGeneratedImage.NAVIGATION_TYPE_PARAM,
                value = type.value,
            )
            put(
                key = ViewGeneratedImage.SKU_ID_PARAM,
                value = activeSKUItem.skuId,
            )
            put(
                key = ViewGeneratedImage.SKU_CATALOG_NAME_PARAM,
                value = activeSKUItem.catalogName,
            )
        }
    }
}

internal fun FashionTryOnController.sendViewMoreToTryOnEvent() {
    analytic.sendEvent(ViewMoreToTryOn)
}

internal fun FashionTryOnController.sendSelectMoreToTryOnEvent(skuItem: SKUItem) {
    analytic.sendEvent(SelectMoreToTryOn) {
        put(
            key = SelectMoreToTryOn.SKU_ID_PARAM,
            value = skuItem.skuId,
        )
        put(
            key = SelectMoreToTryOn.SKU_CATALOG_NAME_PARAM,
            value = skuItem.catalogName,
        )
    }
}

internal fun FashionTryOnController.sendGenerationFeedback(
    generationIndex: Int,
    feedback: String? = null,
) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(GenerationFeedback) {
        put(
            key = GenerationFeedback.SKU_ID_PARAM,
            value = activeSKUItem.skuId,
        )
        put(
            key = GenerationFeedback.SKU_CATALOG_NAME_PARAM,
            value = activeSKUItem.catalogName,
        )
        put(
            key = GenerationFeedback.GENERATED_PHOTO_POSITION_PARAM,
            value = generationIndex.toString(),
        )
        put(
            key = GenerationFeedback.FEEDBACK_PARAM,
            value = feedback,
        )
    }
}

internal fun FashionTryOnController.sendLikeGenerationFeedback(generationIndex: Int) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(LikeGenerationFeedback) {
        put(
            key = LikeGenerationFeedback.SKU_ID_PARAM,
            value = activeSKUItem.skuId,
        )
        put(
            key = LikeGenerationFeedback.SKU_CATALOG_NAME_PARAM,
            value = activeSKUItem.catalogName,
        )
        put(
            key = LikeGenerationFeedback.GENERATED_PHOTO_POSITION_PARAM,
            value = generationIndex.toString(),
        )
    }
}

internal fun FashionTryOnController.sendDislikeGenerationFeedback(generationIndex: Int) {
    val activeSKUItem = activeSKUItem.value

    analytic.sendEvent(DislikeGenerationFeedback) {
        put(
            key = DislikeGenerationFeedback.SKU_ID_PARAM,
            value = activeSKUItem.skuId,
        )
        put(
            key = DislikeGenerationFeedback.SKU_CATALOG_NAME_PARAM,
            value = activeSKUItem.catalogName,
        )
        put(
            key = DislikeGenerationFeedback.GENERATED_PHOTO_POSITION_PARAM,
            value = generationIndex.toString(),
        )
    }
}
