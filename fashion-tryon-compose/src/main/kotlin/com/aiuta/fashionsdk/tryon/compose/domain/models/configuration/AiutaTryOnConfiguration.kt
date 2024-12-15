package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dimensions.AiutaDimensions
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.listeners.DefaultAiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.toggles.AiutaToggles
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.toggles.DefaultAiutaToggles
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow

/**
 * Configuration of Aiuta Try on Flow
 *
 * @see [AiutaTryOnFlow]
 */
@Immutable
public class AiutaTryOnConfiguration private constructor(
    public val dataProvider: AiutaDataProvider?,
    public val dimensions: AiutaDimensions?,
    public val language: AiutaTryOnLanguage,
    public val listeners: AiutaTryOnListeners,
    public val toggles: AiutaToggles,
) {
    /**
     * Public [Builder] for initialize [AiutaTryOnConfiguration] class
     */
    public class Builder {
        private var dataProvider: AiutaDataProvider? = null
        private var dimensions: AiutaDimensions? = null
        private var language: AiutaTryOnLanguage? = null
        private var listeners: AiutaTryOnListeners? = null
        private var toggles: AiutaToggles? = null

        public fun setDataProvider(dataProvider: AiutaDataProvider): Builder {
            return apply { this.dataProvider = dataProvider }
        }

        public fun setDimensions(dimensions: AiutaDimensions): Builder {
            return apply { this.dimensions = dimensions }
        }

        public fun setLanguage(language: AiutaTryOnLanguage): Builder {
            return apply { this.language = language }
        }

        public fun setListeners(listeners: AiutaTryOnListeners): Builder {
            return apply { this.listeners = listeners }
        }

        public fun setToggles(toggles: AiutaToggles): Builder {
            return apply { this.toggles = toggles }
        }

        public fun build(): AiutaTryOnConfiguration {
            // Init default
            val internalToggles = toggles ?: DefaultAiutaToggles
            val internalListeners = listeners ?: DefaultAiutaTryOnListeners

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
                dataProvider = dataProvider,
                dimensions = dimensions,
                language = internalLanguage,
                listeners = internalListeners,
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
