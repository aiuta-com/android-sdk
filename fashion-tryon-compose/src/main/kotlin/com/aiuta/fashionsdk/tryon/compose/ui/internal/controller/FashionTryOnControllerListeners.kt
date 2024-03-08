package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
internal fun FashionTryOnController.skuItemVisibilityListener() {
    LaunchedEffect(currentScreen.value) {
        isSKUItemVisible.value = currentScreen.value in screensWithSKUItems
    }
}
