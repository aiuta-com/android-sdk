package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Immutable

@Immutable
internal class FashionTryOnErrorState(
    val message: String? = null,
) {
    companion object {
        val DEFAULT by lazy { FashionTryOnErrorState() }
    }
}
