package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable

// TODO Docs
@Immutable
public interface AiutaTryOnConfiguration {
    public val photoSelectionLimit: Int

    public val isHistoryAvailable: Boolean
}

public fun defaultAiutaTryOnConfiguration(
    photoSelectionLimit: Int = 10,
    isHistoryAvailable: Boolean = true,
): AiutaTryOnConfiguration {
    return object : AiutaTryOnConfiguration {
        override val photoSelectionLimit: Int = photoSelectionLimit
        override val isHistoryAvailable: Boolean = isHistoryAvailable
    }
}
