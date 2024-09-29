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

public val AiutaIcons.camera24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.camera24
            is AiutaResourceIcons -> this.camera24
        }

public val AiutaIcons.cameraFill24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.cameraFill24
            is AiutaResourceIcons -> this.cameraFill24
        }

public val AiutaIcons.checkCorrect24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.checkCorrect24
            is AiutaResourceIcons -> this.checkCorrect24
        }

public val AiutaIcons.checkNotCorrect24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.checkNotCorrect24
            is AiutaResourceIcons -> this.checkNotCorrect24
        }

public val AiutaIcons.close24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.close24
            is AiutaResourceIcons -> this.close24
        }

public val AiutaIcons.trash24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.trash24
            is AiutaResourceIcons -> this.trash24
        }

public val AiutaIcons.history24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.history24
            is AiutaResourceIcons -> this.history24
        }

public val AiutaIcons.share24: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.share24
            is AiutaResourceIcons -> this.share24
        }

// x20
public val AiutaIcons.wishlist20: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.wishlist20
            is AiutaResourceIcons -> this.wishlist20
        }

public val AiutaIcons.wishlistFill20: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.wishlistFill20
            is AiutaResourceIcons -> this.wishlistFill20
        }

// x16
public val AiutaIcons.magic16: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.magic16
            is AiutaResourceIcons -> this.magic16
        }

public val AiutaIcons.lock16: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.lock16
            is AiutaResourceIcons -> this.lock16
        }

public val AiutaIcons.arrow16: Any
    get() =
        when (this) {
            is AiutaDrawableIcons -> this.arrow16
            is AiutaResourceIcons -> this.arrow16
        }
