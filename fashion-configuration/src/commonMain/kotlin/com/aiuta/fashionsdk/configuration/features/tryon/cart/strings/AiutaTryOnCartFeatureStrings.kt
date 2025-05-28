package com.aiuta.fashionsdk.configuration.features.tryon.cart.strings

/**
 * Interface for cart-related text strings in the try-on feature.
 *
 * This interface defines the text strings used in the cart interface,
 * allowing for localization and customization of user-facing text.
 *
 * @property addToCart Text displayed on the add to cart button
 */
public interface AiutaTryOnCartFeatureStrings {
    public val addToCart: String

    /**
     * Default implementation of [AiutaTryOnCartFeatureStrings].
     */
    public class Default : AiutaTryOnCartFeatureStrings {
        override val addToCart: String = "Add to cart"
    }
}
