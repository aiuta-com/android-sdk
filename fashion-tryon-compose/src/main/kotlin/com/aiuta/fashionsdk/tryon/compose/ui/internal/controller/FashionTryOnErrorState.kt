package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Immutable

@Immutable
internal class FashionTryOnErrorState(
    val message: String = DEFAULT_MESSAGE,
) {
    companion object {
        val DEFAULT by lazy { FashionTryOnErrorState() }
        private const val DEFAULT_MESSAGE = "Something went wrong.\nPlease try again later"
    }
}
