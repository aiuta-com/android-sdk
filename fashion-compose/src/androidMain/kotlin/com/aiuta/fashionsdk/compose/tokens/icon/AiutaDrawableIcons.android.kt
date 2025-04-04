package com.aiuta.fashionsdk.compose.tokens.icon

import android.graphics.drawable.Drawable

/**
 * Public class for providing drawable icons in the SDK.
 *
 * This class allows the host application to override the default drawable icons used by the SDK,
 * enabling a more personalized user experience.
 *
 * The icons are categorized by their sizes (e.g., 82x, 36x, 24x, 20x, 16x).
 *
 * @see AiutaIcons
 */
public class AiutaDrawableIcons(
    // x36
    public override val error36: AiutaDrawableIcon,
    public override val imageError36: AiutaDrawableIcon,
    // x24
    public override val back24: AiutaDrawableIcon,
    public override val close24: AiutaDrawableIcon,
    public override val trash24: AiutaDrawableIcon,
    // x20
    public override val check20: AiutaDrawableIcon,
) : AiutaIcons

public class AiutaDrawableIcon(
    public override val resource: Drawable,
    public override val shouldDrawAsIs: Boolean = false,
) : AiutaIcon
