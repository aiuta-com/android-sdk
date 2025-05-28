package com.aiuta.fashionsdk.configuration.ui.theme.image

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.image.icons.AiutaImageThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.image.shapes.AiutaImageThemeShapes

/**
 * Image theme configuration for the Aiuta SDK.
 * 
 * This immutable class defines the visual styling for images and icons throughout
 * the SDK, including shape and icon configurations. It provides a consistent image
 * appearance that can be customized to match your app's design system.
 * 
 * 
 * @property shapes Shape configuration for image containers and overlays
 * @property icons Icon configuration for image-related UI elements
 * @see AiutaImageThemeShapes
 * @see AiutaImageThemeIcons
 */
@Immutable
public class AiutaImageTheme(
    public val shapes: AiutaImageThemeShapes,
    public val icons: AiutaImageThemeIcons,
) {
    /**
     * Builder class for creating [AiutaImageTheme] instances.
     * 
     * This builder ensures all required theme components are set before creating
     * the final image theme configuration.
     */
    public class Builder {
        /**
         * Shape configuration for image containers and overlays.
         */
        public var shapes: AiutaImageThemeShapes? = null

        /**
         * Icon configuration for image-related UI elements.
         */
        public var icons: AiutaImageThemeIcons? = null

        /**
         * Creates an [AiutaImageTheme] instance with the configured properties.
         * 
         * @return Configured [AiutaImageTheme] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaImageTheme {
            val parentClass = "AiutaImageTheme"

            return AiutaImageTheme(
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring image theme within an [AiutaTheme.Builder].
 * 
 * This extension provides a convenient DSL for configuring the image theme
 * as part of the main theme setup.
 * 
 * ```kotlin
 * theme {
 *     image {
 *         shapes = AiutaImageThemeShapes.Default()
 *         icons = AiutaImageThemeIcons.Default()
 *     }
 *     // Configure other theme components...
 * }
 * ```
 * 
 * @param block Configuration block for setting up the image theme
 * @return The theme builder for method chaining
 * @see AiutaTheme.Builder
 * @see AiutaImageTheme.Builder
 */
public inline fun AiutaTheme.Builder.image(
    block: AiutaImageTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    image = AiutaImageTheme.Builder().apply(block).build()
}
