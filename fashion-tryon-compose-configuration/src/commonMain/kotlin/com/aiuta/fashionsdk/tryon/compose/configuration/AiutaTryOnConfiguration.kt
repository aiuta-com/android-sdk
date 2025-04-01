package com.aiuta.fashionsdk.tryon.compose.configuration

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.tryon.compose.configuration.AiutaTryOnConfiguration.Builder
import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.dimensions.AiutaDimensions
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.analytic.sendConfigurationEvent
import com.aiuta.fashionsdk.tryon.compose.configuration.language.AiutaTryOnLanguage
import com.aiuta.fashionsdk.tryon.compose.configuration.meta.DefaultHostMetadata
import com.aiuta.fashionsdk.tryon.compose.configuration.meta.HostMetadata
import com.aiuta.fashionsdk.tryon.compose.configuration.toggles.AiutaToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.toggles.DefaultAiutaToggles
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.tryon

/**
 * Configuration of Aiuta Try on Flow
 *
 */
@Immutable
public class AiutaTryOnConfiguration private constructor(
    public val aiuta: Aiuta,
    public val features: AiutaTryOnFeatures,
    @Deprecated("Will be split by features")
    public val dataProvider: AiutaDataProvider?,
    @Deprecated("Will be split by features")
    public val dimensions: AiutaDimensions?,
    @Deprecated("Will be split by features")
    public val language: AiutaTryOnLanguage,
    @Deprecated("Will be split by features")
    public val hostMetadata: HostMetadata,
    @Deprecated("Will be split by features")
    public val toggles: AiutaToggles,
) {
    public val aiutaTryOn: AiutaTryOn by lazy { aiuta.tryon }
    public val aiutaAnalytic: InternalAiutaAnalytic by lazy { aiuta.internalAiutaAnalytic }

    /**
     * Public [Builder] for initialize [AiutaTryOnConfiguration] class
     */
    public class Builder {
        public var aiuta: Aiuta? = null
        public var features: AiutaTryOnFeatures? = null
        public var dataProvider: AiutaDataProvider? = null
        public var dimensions: AiutaDimensions? = null
        public var language: AiutaTryOnLanguage? = null
        public var hostMetadata: HostMetadata? = null
        public var toggles: AiutaToggles? = null

        public fun setAiuta(aiuta: Aiuta): Builder {
            return apply { this.aiuta = aiuta }
        }

        public fun setFeatures(features: AiutaTryOnFeatures): Builder {
            return apply { this.features = features }
        }

        @Deprecated("Will be split by features")
        public fun setDataProvider(dataProvider: AiutaDataProvider): Builder {
            return apply { this.dataProvider = dataProvider }
        }

        @Deprecated("Will be split by features")
        public fun setDimensions(dimensions: AiutaDimensions): Builder {
            return apply { this.dimensions = dimensions }
        }

        @Deprecated("Will be split by features")
        public fun setLanguage(language: AiutaTryOnLanguage): Builder {
            return apply { this.language = language }
        }

        @Deprecated("Will be split by features")
        public fun setHostMetadata(hostMetadata: HostMetadata): Builder {
            return apply { this.hostMetadata = hostMetadata }
        }

        @Deprecated("Will be split by features")
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
            val internalFeatures =
                this.features.checkNotNullWithDescription(
                    property = "features",
                    methodToCall = "setFeatures()",
                )

            return AiutaTryOnConfiguration(
                aiuta = internalAiuta,
                features = internalFeatures,
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

public inline fun aiutaTryOnConfiguration(block: Builder.() -> Unit): AiutaTryOnConfiguration =
    Builder().apply(block).build()
