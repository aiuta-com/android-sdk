package com.aiuta.fashionsdk.compose.tokens.icon

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

/**
 * Public interface for providing the possibility of
 * customizing icons in the SDK.
 *
 * This interface allows the host application to override
 * the default icons used by the SDK, enabling a more
 * personalized user experience.
 *
 * @see AiutaTheme
 */
@Immutable
public sealed interface AiutaIcons

// x82

public val AiutaIcons.welcomeScreen82: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.welcomeScreen82
            is AiutaResourceIcons -> this.welcomeScreen82
        }

// x36
public val AiutaIcons.error36: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.error36
            is AiutaResourceIcons -> this.error36
        }

public val AiutaIcons.like36: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.like36
            is AiutaResourceIcons -> this.like36
        }

public val AiutaIcons.dislike36: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.dislike36
            is AiutaResourceIcons -> this.dislike36
        }

// x24
public val AiutaIcons.back24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.back24
            is AiutaResourceIcons -> this.back24
        }

public val AiutaIcons.close24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.close24
            is AiutaResourceIcons -> this.close24
        }
