package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.domain.internal.analytic.configure.sendConfigurationEvent
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dimensions.AiutaDimensions
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.meta.DefaultHostMetadata
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.meta.HostMetadata
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.toggles.AiutaToggles
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.toggles.DefaultAiutaToggles
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import com.aiuta.fashionsdk.tryon.core.tryon

/**
 * Configuration of Aiuta Try on Flow
 *
 * @see [AiutaTryOnFlow]
 */
@Immutable
public class AiutaTryOnConfiguration private constructor(
    public val aiuta: Aiuta,
    public val dataProvider: AiutaDataProvider?,
    public val dimensions: AiutaDimensions?,
    public val language: AiutaTryOnLanguage,
    public val hostMetadata: HostMetadata,
    public val toggles: AiutaToggles,
) {
    internal val aiutaTryOn by lazy { aiuta.tryon }
    internal val aiutaAnalytic by lazy { aiuta.internalAiutaAnalytic }

    /**
     * Public [Builder] for initialize [AiutaTryOnConfiguration] class
     */
    public class Builder {
        private var aiuta: Aiuta? = null
        private var dataProvider: AiutaDataProvider? = null
        private var dimensions: AiutaDimensions? = null
        private var language: AiutaTryOnLanguage? = null
        private var hostMetadata: HostMetadata? = null
        private var toggles: AiutaToggles? = null

        public fun setAiuta(aiuta: Aiuta): Builder {
            return apply { this.aiuta = aiuta }
        }

        public fun setDataProvider(dataProvider: AiutaDataProvider): Builder {
            return apply { this.dataProvider = dataProvider }
        }

        public fun setDimensions(dimensions: AiutaDimensions): Builder {
            return apply { this.dimensions = dimensions }
        }

        public fun setLanguage(language: AiutaTryOnLanguage): Builder {
            return apply { this.language = language }
        }

        public fun setHostMetadata(hostMetadata: HostMetadata): Builder {
            return apply { this.hostMetadata = hostMetadata }
        }

        public fun setToggles(toggles: AiutaToggles): Builder {
            return apply { this.toggles = toggles }
        }

        public fun build(): AiutaTryOnConfiguration {
            // Init default
            val internalToggles = toggles ?: DefaultAiutaToggles
            val internalHostMetadata = hostMetadata ?: DefaultHostMetadata

            // Check props without default initialization
            val internalAiuta =
                this.aiuta.checkNotNullWithDescription(
                    property = "aiuta",
                    methodToCall = "setAiuta()",
                )
            val internalLanguage =
                this.language.checkNotNullWithDescription(
                    property = "language",
                    methodToCall = "setLanguage()",
                )

            return AiutaTryOnConfiguration(
                aiuta = internalAiuta,
                dataProvider = dataProvider,
                dimensions = dimensions,
                language = internalLanguage,
                hostMetadata = internalHostMetadata,
                toggles = internalToggles,
            ).also {
                it.sendConfigurationEvent()
            }
        }
    }

    private companion object {
        fun <T> T?.checkNotNullWithDescription(
            property: String,
            methodToCall: String,
        ): T {
            return checkNotNull(
                value = this,
                lazyMessage = {
                    propertyIsNull(
                        property = property,
                        methodToCall = methodToCall,
                    )
                },
            )
        }

        fun propertyIsNull(
            property: String,
            methodToCall: String,
        ): String {
            return """
                AiutaTryOnConfiguration: $property is null, therefore cannot init AiutaTryOnConfiguration.
                Please, call $methodToCall before build()
                """.trimIndent()
        }
    }
}
