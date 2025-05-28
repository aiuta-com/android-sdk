package com.aiuta.fashionsdk.configuration.features.wishlist

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.wishlist.dataprovider.AiutaWishlistFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.wishlist.icons.AiutaWishlistFeatureIcons
import com.aiuta.fashionsdk.configuration.features.wishlist.strings.AiutaWishlistFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the wishlist feature in the fashion SDK.
 * 
 * This feature provides functionality for users to save and manage their favorite items,
 * including icons, text strings, and data provider for wishlist operations.
 * 
 * Required components:
 * - [icons]: Icons used in the wishlist UI
 * - [strings]: Text strings for the wishlist interface
 * - [dataProvider]: Provider for wishlist data operations
 */
public class AiutaWishlistFeature(
    public val icons: AiutaWishlistFeatureIcons,
    public val strings: AiutaWishlistFeatureStrings,
    public val dataProvider: AiutaWishlistFeatureDataProvider,
) : AiutaFeature {

    /**
     * Builder class for creating instances of [AiutaWishlistFeature].
     * 
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaWishlistFeatureIcons? = null
        public var strings: AiutaWishlistFeatureStrings? = null
        public var dataProvider: AiutaWishlistFeatureDataProvider? = null

        public override fun build(): AiutaWishlistFeature {
            val parentClass = "AiutaWishlistFeature"

            return AiutaWishlistFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                dataProvider = dataProvider.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "dataProvider",
                ),
            )
        }
    }
}

/**
 * DSL function for configuring the wishlist feature.
 * 
 * Example usage:
 * ```
 * features {
 *     wishlist {
 *         // Configure wishlist feature
 *         icons = ...
 *         strings = ...
 *         dataProvider = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaFeatures.Builder.wishlist(
    block: AiutaWishlistFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    wishlist = AiutaWishlistFeature.Builder().apply(block).build()
}
