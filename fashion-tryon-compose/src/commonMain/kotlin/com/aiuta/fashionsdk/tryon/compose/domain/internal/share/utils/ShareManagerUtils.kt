package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils

import kotlinx.datetime.Clock

internal const val PAGE_ID_KEY = "pageIdKey"
internal const val PRODUCT_ID_KEY = "productIdKey"
internal const val SHARE_REQUEST_CODE = 100

internal fun generateImageFileName() = "image_${Clock.System.now().toEpochMilliseconds()}.png"
