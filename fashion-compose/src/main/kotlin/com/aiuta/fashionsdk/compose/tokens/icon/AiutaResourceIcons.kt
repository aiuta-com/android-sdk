package com.aiuta.fashionsdk.compose.tokens.icon

import androidx.annotation.DrawableRes

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
    // x82
    @DrawableRes public val welcomeScreen82: Int,
    // x36
    @DrawableRes public val error36: Int,
    @DrawableRes public val like36: Int,
    @DrawableRes public val dislike36: Int,
    // x24
    @DrawableRes public val back24: Int,
    @DrawableRes public val camera24: Int,
    @DrawableRes public val cameraFill24: Int,
    @DrawableRes public val close24: Int,
    @DrawableRes public val trash24: Int,
    @DrawableRes public val history24: Int,
    @DrawableRes public val share24: Int,
    // x20
    @DrawableRes public val wishlist20: Int,
    @DrawableRes public val wishlistFill20: Int,
    // x16
    @DrawableRes public val magic16: Int,
    @DrawableRes public val lock16: Int,
    @DrawableRes public val arrow16: Int,
) : AiutaIcons
