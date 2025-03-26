package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.paging

internal object PagingPlaceholderContentType

internal data class PagingPlaceholderKey(val index: Int)

internal fun getPagingPlaceholderKey(index: Int) = PagingPlaceholderKey(index)
