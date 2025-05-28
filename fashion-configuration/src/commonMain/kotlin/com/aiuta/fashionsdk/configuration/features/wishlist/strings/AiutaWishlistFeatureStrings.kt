package com.aiuta.fashionsdk.configuration.features.wishlist.strings

/**
 * Interface defining text strings used in the wishlist feature.
 * 
 * This interface provides strings for buttons and other UI elements
 * in the wishlist interface.
 */
public interface AiutaWishlistFeatureStrings {
    /**
     * Text for the button that adds an item to the wishlist.
     */
    public val wishlistButtonAdd: String

    /**
     * Default implementation of [AiutaWishlistFeatureStrings].
     * 
     * Provides standard English text for the wishlist interface.
     */
    public class Default : AiutaWishlistFeatureStrings {
        override val wishlistButtonAdd: String = "Wishlist"
    }
}
