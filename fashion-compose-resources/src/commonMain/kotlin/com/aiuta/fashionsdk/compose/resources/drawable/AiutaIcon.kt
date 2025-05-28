package com.aiuta.fashionsdk.compose.resources.drawable

import androidx.compose.runtime.Immutable

/**
 * Immutable class representing an icon with rendering configuration.
 *
 * This class wraps an [AiutaDrawableResource] and provides additional metadata
 * about how the icon should be rendered, particularly whether it should be
 * drawn as-is or can be modified (tinted).
 *
 * ```kotlin
 * // Icon that can be tinted
 * val editIcon = AiutaIcon(
 *     iconResource = AiutaComposeDrawableResource(Res.drawable.ic_edit),
 *     shouldDrawAsIs = false
 * )
 *
 * // Icon that should be drawn exactly as provided (e.g., logo, photo)
 * val logoIcon = AiutaIcon(
 *     iconResource = AiutaComposeDrawableResource(Res.drawable.company_logo),
 *     shouldDrawAsIs = true
 * )
 *
 * // Using default behavior (can be modified)
 * val defaultIcon = AiutaIcon(
 *     iconResource = AiutaAndroidDrawableRes(R.drawable.ic_star)
 * )
 * ```
 *
 * @property iconResource The drawable resource containing the icon
 * @property shouldDrawAsIs Whether the icon should be drawn exactly as provided without modifications
 * @see AiutaDrawableResource
 */
@Immutable
public class AiutaIcon(
    public val iconResource: AiutaDrawableResource,
    public val shouldDrawAsIs: Boolean = false,
)
