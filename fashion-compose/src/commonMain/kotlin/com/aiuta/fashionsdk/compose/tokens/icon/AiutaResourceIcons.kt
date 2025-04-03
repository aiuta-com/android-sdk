package com.aiuta.fashionsdk.compose.tokens.icon

import org.jetbrains.compose.resources.DrawableResource

/**
 * Public class for providing resource icons in the SDK.
 *
 * This class allows the host application to override the default resource icons used by the SDK,
 * enabling a more personalized user experience.
 *
 * The icons are categorized by their sizes (e.g., 82x, 36x, 24x, 20x, 16x).
 *
 * @see AiutaIcons
 */
public class AiutaResourceIcons(
    // x100
    public override val recent100: AiutaResourceIcon?,
    // x36
    public override val error36: AiutaResourceIcon,
    public override val imageError36: AiutaResourceIcon,
    // x24
    public override val back24: AiutaResourceIcon,
    public override val camera24: AiutaResourceIcon,
    public override val close24: AiutaResourceIcon,
    public override val magic20: AiutaResourceIcon,
    public override val trash24: AiutaResourceIcon,
    public override val share24: AiutaResourceIcon,
    public override val wishlist24: AiutaResourceIcon,
    public override val wishlistFill24: AiutaResourceIcon,
    // x20
    public override val check20: AiutaResourceIcon,
    // x16
    public override val lock16: AiutaResourceIcon,
    public override val arrow16: AiutaResourceIcon,
) : AiutaIcons

public class AiutaResourceIcon(
    public override val resource: DrawableResource,
    public override val shouldDrawAsIs: Boolean = false,
) : AiutaIcon
