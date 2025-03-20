package com.aiuta.fashionsdk.network.utils

import kotlinx.serialization.json.Json

internal val jsonSerializer =
    Json {
        ignoreUnknownKeys = true
    }
