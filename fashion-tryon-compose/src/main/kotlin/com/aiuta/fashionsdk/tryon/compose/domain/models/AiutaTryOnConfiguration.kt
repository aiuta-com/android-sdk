package com.aiuta.fashionsdk.tryon.compose.domain.models

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.dimensions.AiutaDimensions
import com.aiuta.fashionsdk.tryon.compose.domain.models.toggles.AiutaToggles
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

/**
 * Configuration of Aiuta Try on Flow
 *
 * @see [AiutaTryOnFlow]
 */
@Immutable
public class AiutaTryOnConfiguration private constructor(
    public val language: AiutaTryOnLanguage,
    public val dimensions: AiutaDimensions?,
    public val dataProvider: AiutaDataProvider?,
    public val toggles: AiutaToggles,
) {
    /**
     * Public [Builder] for initialize [AiutaTryOnConfiguration] class
     */
    public class Builder {
        private var language: AiutaTryOnLanguage? = null
        private var dimensions: AiutaDimensions? = null
        private var dataProvider: AiutaDataProvider? = null
        private var toggles: AiutaToggles? = null

        public fun setLanguage(language: AiutaTryOnLanguage): Builder {
            return apply { this.language = language }
        }

        public fun setDimensions(dimensions: AiutaDimensions): Builder {
            return apply { this.dimensions = dimensions }
        }

        public fun setDataProvider(dataProvider: AiutaDataProvider): Builder {
            return apply { this.dataProvider = dataProvider }
        }

        public fun setToggles(toggles: AiutaToggles): Builder {
            return apply { this.toggles = toggles }
        }

        public fun build(): AiutaTryOnConfiguration {
            // Init default
            val internalToggles = toggles ?: AiutaToggles()

            // Check props without default initialization
            val internalLanguage = this.language

            checkNotNull(
                value = internalLanguage,
                lazyMessage = {
                    propertyIsNull(
                        property = "language",
                        methodToCall = "setLanguage()",
                    )
                },
            )

            return AiutaTryOnConfiguration(
                language = internalLanguage,
                dimensions = dimensions,
                dataProvider = dataProvider,
                toggles = internalToggles,
            ).also {
                // TODO Add analytic
            }
        }
    }

    private companion object {
        fun propertyIsNull(
            property: String,
            methodToCall: String,
        ): String {
            return "AiutaTryOnConfiguration: $property is null, therefore cannot init AiutaTryOnConfiguration. " +
                "Please, call $methodToCall before build()"
        }
    }
}
