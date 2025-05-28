package com.aiuta.fashionsdk.configuration.features.wishlist.icons

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon

/**
 * Interface defining icons used in the wishlist feature.
 * 
 * This interface provides icons for the wishlist UI elements,
 * including both outlined and filled versions of the wishlist icon.
 */
public interface AiutaWishlistFeatureIcons {
    /**
     * 24x24 pixel outlined wishlist icon.
     * Used to indicate that an item can be added to the wishlist.
     */
    public val wishlist24: AiutaIcon

    /**
     * 24x24 pixel filled wishlist icon.
     * Used to indicate that an item is already in the wishlist.
     */
    public val wishlistFill24: AiutaIcon
}
