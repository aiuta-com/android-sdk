package com.aiuta.fashionsdk.tryon.compose.domain.models.internal.config

import androidx.compose.runtime.Immutable

@Immutable
internal class ClientConfig(
    val etag: String? = null,
    val clientConfiguration: ClientConfiguration,
)
