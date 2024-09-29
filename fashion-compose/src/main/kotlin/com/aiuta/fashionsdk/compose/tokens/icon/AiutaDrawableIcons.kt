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
    // x82
    public val welcomeScreen82: Drawable,
    // x36
    public val error36: Drawable,
    public val like36: Drawable,
    public val dislike36: Drawable,
    // x24
    public val back24: Drawable,
    public val camera24: Drawable,
    public val cameraFill24: Drawable,
    public val checkCorrect24: Drawable,
    public val checkNotCorrect24: Drawable,
    public val close24: Drawable,
    public val trash24: Drawable,
    public val history24: Drawable,
    public val share24: Drawable,
    // x20
    public val wishlist20: Drawable,
    public val wishlistFill20: Drawable,
    // x16
    public val magic16: Drawable,
    public val lock16: Drawable,
    public val arrow16: Drawable,
) : AiutaIcons
