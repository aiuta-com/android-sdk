package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

/**
 * Configuration of Aiuta Try on Flow
 *
 * @see [AiutaTryOnFlow]
 */
@Immutable
public interface AiutaTryOnConfiguration {
    /**
     * The maximum number of photos that a user can select
     * in the system picket for virtual try on.
     */
    public val photoSelectionLimit: Int

    /**
     * Flag which turn on or off possibility to use history flow
     * inside [AiutaTryOnFlow].
     *
     * Be careful - if you turn off this flag, all previous
     * generation history will be deleted
     */
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
