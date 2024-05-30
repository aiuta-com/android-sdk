package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.repository.config.ConfigRepository

@Composable
internal fun rememberAiutaTryOnDataController(aiuta: () -> Aiuta): AiutaTryOnDataController {
    val configRepository =
        remember {
            ConfigRepository.getInstance(aiuta())
        }

    return remember {
        AiutaTryOnDataController(
            configRepository = configRepository,
        )
    }
}

@Immutable
internal class AiutaTryOnDataController(
    val configRepository: ConfigRepository,
)
