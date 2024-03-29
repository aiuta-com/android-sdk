package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytic.model.Configure
import com.aiuta.fashionsdk.analytic.model.StartOnBoarding
import com.aiuta.fashionsdk.analytic.model.StartSession
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

// Configure
@Composable
internal fun sendConfigureEvent(theme: (() -> AiutaTryOnTheme)?) {
    val analytic = LocalAnalytic.current
    LaunchedEffect(Unit) {
        analytic.sendEvent(Configure) {
            put(
                key = Configure.HAS_CUSTOM_CONFIGURATION_PARAM,
                value = (theme != null).toString(),
            )
            put(
                key = Configure.PHOTO_LIMIT_PARAM,
                value = "0", // TODO Unmock with multi selector
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
