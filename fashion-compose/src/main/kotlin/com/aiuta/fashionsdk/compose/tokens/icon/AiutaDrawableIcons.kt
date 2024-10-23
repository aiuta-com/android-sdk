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
    // x100
    public override val recent100: AiutaDrawableIcon,
    // x82
    public override val welcomeScreen82: AiutaDrawableIcon,
    // x36
    public override val error36: AiutaDrawableIcon,
    public override val like36: AiutaDrawableIcon,
    public override val dislike36: AiutaDrawableIcon,
    // x24
    public override val back24: AiutaDrawableIcon,
    public override val camera24: AiutaDrawableIcon,
    public override val checkCorrect24: AiutaDrawableIcon,
    public override val checkNotCorrect24: AiutaDrawableIcon,
    public override val close24: AiutaDrawableIcon,
    public override val trash24: AiutaDrawableIcon,
    public override val takePhoto24: AiutaDrawableIcon,
    public override val history24: AiutaDrawableIcon,
    public override val photoLibrary24: AiutaDrawableIcon,
    public override val share24: AiutaDrawableIcon,
    public override val wishlist24: AiutaDrawableIcon,
    public override val wishlistFill24: AiutaDrawableIcon,
    // x20
    public override val info20: AiutaDrawableIcon,
    // x16
    public override val check16: AiutaDrawableIcon,
    public override val magic16: AiutaDrawableIcon,
    public override val lock16: AiutaDrawableIcon,
    public override val arrow16: AiutaDrawableIcon,
    // x14
    public override val loading14: AiutaDrawableIcon,
) : AiutaIcons {
    public class AiutaDrawableIcon(
        public override val resource: Drawable,
        public override val shouldDrawAsIs: Boolean = false,
    ) : AiutaIcons.AiutaIcon
}
