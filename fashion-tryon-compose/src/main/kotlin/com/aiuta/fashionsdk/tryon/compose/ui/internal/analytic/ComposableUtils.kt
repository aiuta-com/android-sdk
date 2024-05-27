package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.Configure
import com.aiuta.fashionsdk.internal.analytic.model.StartOnBoarding
import com.aiuta.fashionsdk.internal.analytic.model.StartSession
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

// Configure
@Composable
internal fun sendConfigureEvent(theme: (() -> AiutaTryOnTheme)?) {
    val analytic = LocalAnalytic.current
    val configuration = LocalAiutaConfiguration.current

    LaunchedEffect(Unit) {
        analytic.sendEvent(Configure) {
            put(
                key = Configure.HAS_CUSTOM_CONFIGURATION_PARAM,
                value = (theme != null).toString(),
            )
            put(
                key = Configure.PHOTO_LIMIT_PARAM,
                value = configuration.photoSelectionLimit.toString(),
            )
            put(
                key = Configure.IS_WATERMARK_PROVIDED_PARAM,
                value = (theme?.invoke()?.watermarkRes != null).toString(),
            )
            put(
                key = Configure.IS_LOGO_PROVIDED_PARAM,
                value = (theme?.invoke()?.navBarTheme?.navLogo != null).toString(),
            )
            put(
                key = Configure.IS_HISTORY_ENABLE_PARAM,
                value = configuration.isHistoryAvailable.toString(),
            )
            put(
                key = Configure.IS_POWERED_BY_VISIBLE_PARAM,
                value = true.toString(), // TODO Unmock with remote config
            )
        }
    }
}

// Session
@Composable
internal fun sendStartSessionEvent() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current
    val skuItem = controller.activeSKUItem.value

    LaunchedEffect(Unit) {
        analytic.sendEvent(StartSession) {
            put(
                key = StartSession.SKU_ID_PARAM,
                value = skuItem.skuId,
            )
            put(
                key = StartSession.SKU_CATALOG_NAME_PARAM,
                value = skuItem.catalogName,
            )
            put(
                key = StartSession.RELATED_SKU_COUNT_PARAM,
                value = (skuItem.generateMoreSKU?.size ?: 0).toString(),
            )
            put(
                key = StartSession.PRICE_PARAM,
                value = skuItem.price?.toString(),
            )
            put(
                key = StartSession.PRICE_DISCOUNTED_PARAM,
                value = skuItem.priceDiscounted?.toString(),
            )
            put(
                key = StartSession.STORE_PARAM,
                value = skuItem.store,
            )
            put(
                key = StartSession.ADDITIONAL_SHARE_INFO_PARAM,
                value = skuItem.additionalShareInfo,
            )
        }
    }
}

// Onboarding
@Composable
internal fun sendStartOnBoardingEvent() {
    val analytic = LocalAnalytic.current
    LaunchedEffect(Unit) {
        analytic.sendEvent(StartOnBoarding)
    }
}
