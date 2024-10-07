package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.internal.analytic.model.Configure
import com.aiuta.fashionsdk.internal.analytic.model.StartOnBoarding
import com.aiuta.fashionsdk.internal.analytic.model.StartSession
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.providePoweredByUrl

// Configure
@Composable
internal fun sendConfigureEvent(theme: AiutaTheme?) {
    val analytic = LocalAnalytic.current
    val configuration = LocalAiutaConfiguration.current
    val dataController = LocalAiutaTryOnDataController.current

    LaunchedEffect(Unit) {
        val isPoweredByVisible = dataController.providePoweredByUrl() != null

        analytic.sendEvent(Configure) {
            put(
                key = Configure.HAS_CUSTOM_CONFIGURATION_PARAM,
                value = (theme != null).toString(),
            )
            put(
                key = Configure.PHOTO_LIMIT_PARAM,
                value = "1",
            )
            put(
                key = Configure.IS_WATERMARK_PROVIDED_PARAM,
                value = (theme?.watermarkRes != null).toString(),
            )
            put(
                key = Configure.IS_LOGO_PROVIDED_PARAM,
                value = (theme?.navBarTheme?.navLogo != null).toString(),
            )
            put(
                key = Configure.IS_HISTORY_ENABLE_PARAM,
                value = configuration.isHistoryAvailable.toString(),
            )
            put(
                key = Configure.IS_POWERED_BY_VISIBLE_PARAM,
                value = isPoweredByVisible.toString(),
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
                value = skuItem.localizedPrice,
            )
            put(
                key = StartSession.PRICE_DISCOUNTED_PARAM,
                value = skuItem.localizedOldPrice,
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
