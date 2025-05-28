package com.aiuta.fashionsdk.compose.resources.drawable

import org.jetbrains.skia.Image

/**
 * Native platform implementation of [AiutaDrawableResource] using Skia [Image].
 *
 * This implementation is used on native platforms (iOS, macOS, Linux, etc.)
 * where Skia is the underlying graphics engine. It allows you to use Skia
 * [Image] objects directly within the Aiuta SDK.
 *
 *
 * @property resource The Skia [Image] instance
 * @see Image
 * @see AiutaDrawableResource
 */
public class AiutaIOSSkia(
    public override val resource: Image,
) : AiutaDrawableResource
