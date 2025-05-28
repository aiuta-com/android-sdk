package com.aiuta.fashionsdk.configuration.features.tryon.cart.handler

/**
 * Interface for handling cart-related actions in the try-on feature.
 * 
 * This interface defines the contract for implementing cart functionality,
 * allowing integration with external shopping cart systems.
 * 
 * @property addToCart Adds a product to the shopping cart
 * @param productId Identifier of the product to add to cart
 */
public interface AiutaTryOnCartFeatureHandler {

    public fun addToCart(productId: String)
}
