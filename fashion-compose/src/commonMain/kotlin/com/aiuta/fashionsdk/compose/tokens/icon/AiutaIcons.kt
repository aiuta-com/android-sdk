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
public interface AiutaIcons {

    // x36
    public val error36: AiutaIcon
    public val imageError36: AiutaIcon

    // x24
    public val back24: AiutaIcon
    public val close24: AiutaIcon
    public val trash24: AiutaIcon

    // x20
    public val check20: AiutaIcon
}

@Immutable
public interface AiutaIcon {
    public val resource: Any?
    public val shouldDrawAsIs: Boolean
}
