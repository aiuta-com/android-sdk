package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.foundation.pager.PagerState

internal fun PagerState.calculateCurrentOffsetForPage(page: Int): Float = (currentPage - page) + currentPageOffsetFraction
