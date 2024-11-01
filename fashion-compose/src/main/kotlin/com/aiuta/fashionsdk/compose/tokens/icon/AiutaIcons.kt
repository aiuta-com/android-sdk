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
public sealed interface AiutaIcons {
    @Immutable
    public interface AiutaIcon {
        public val resource: Any
        public val shouldDrawAsIs: Boolean
    }

    // x100
    public val recent100: AiutaIcon?

    // x82
    public val welcomeScreen82: AiutaIcon

    // x36
    public val error36: AiutaIcon
    public val like36: AiutaIcon
    public val dislike36: AiutaIcon
    public val imageError36: AiutaIcon

    // x24
    public val back24: AiutaIcon
    public val camera24: AiutaIcon
    public val checkCorrect24: AiutaIcon
    public val checkNotCorrect24: AiutaIcon
    public val close24: AiutaIcon
    public val history24: AiutaIcon
    public val photoLibrary24: AiutaIcon
    public val trash24: AiutaIcon
    public val takePhoto24: AiutaIcon
    public val share24: AiutaIcon
    public val wishlist24: AiutaIcon
    public val wishlistFill24: AiutaIcon

    // x20
    public val check20: AiutaIcon
    public val info20: AiutaIcon

    // x16
    public val magic16: AiutaIcon
    public val lock16: AiutaIcon
    public val arrow16: AiutaIcon

    // x14
    public val loading14: AiutaIcon
}
