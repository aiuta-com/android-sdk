package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.remote.config

import androidx.compose.runtime.Immutable

@Immutable
internal class ClientConfig(
    val etag: String? = null,
    val clientConfiguration: ClientConfiguration,
)
