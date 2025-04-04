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
    // x36
    public override val error36: AiutaResourceIcon,
    public override val imageError36: AiutaResourceIcon,
    // x24
    public override val back24: AiutaResourceIcon,
    public override val close24: AiutaResourceIcon,
    public override val trash24: AiutaResourceIcon,
    // x20
    public override val check20: AiutaResourceIcon,
) : AiutaIcons

public class AiutaResourceIcon(
    public override val resource: DrawableResource,
    public override val shouldDrawAsIs: Boolean = false,
) : AiutaIcon
