package com.aiuta.fashionsdk.configuration.features.tryon.history

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.history.dataprovider.AiutaTryOnGenerationsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.tryon.history.icons.AiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.configuration.features.tryon.history.strings.AiutaTryOnGenerationsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the try-on generations history feature.
 *
 * This feature manages the display and interaction with previously generated try-on results,
 * allowing users to view and manage their try-on history.
 *
 * Required components:
 * - [icons]: Icon resources for the history interface
 * - [strings]: Text content for the history interface
 * - [dataProvider]: Provider for accessing and managing history data
 *
 * @property icons Icon resources for the history interface
 * @property strings Text content for the history interface
 * @property dataProvider Provider for accessing and managing history data
 */
public class AiutaTryOnGenerationsHistoryFeature(
    public val icons: AiutaTryOnGenerationsHistoryFeatureIcons,
    public val strings: AiutaTryOnGenerationsHistoryFeatureStrings,
    public val dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider,
) : AiutaFeature {

    /**
     * Builder for creating [AiutaTryOnGenerationsHistoryFeature] instances.
     *
     * This builder ensures all required properties are set before creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaTryOnGenerationsHistoryFeatureIcons? = null
        public var strings: AiutaTryOnGenerationsHistoryFeatureStrings? = null
        public var dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaTryOnGenerationsHistoryFeature {
            val parentClass = "AiutaTryOnGenerationsHistoryFeature"

            return AiutaTryOnGenerationsHistoryFeature(
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
 * DSL function for configuring the generations history feature.
 *
 * This function allows for DSL-style configuration of the generations history feature
 * within the try-on feature configuration.
 *
 * ```kotlin
 * tryOn {
 *     generationsHistory {
 *         icons = ...
 *         strings = ...
 *         dataProvider = ...
 *     }
 * }
 * ```
 *
 * @param block Configuration block for the generations history feature
 * @return The updated try-on feature builder
 */
public inline fun AiutaTryOnFeature.Builder.generationsHistory(
    block: AiutaTryOnGenerationsHistoryFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    generationsHistory = AiutaTryOnGenerationsHistoryFeature.Builder().apply(block).build()
}
