package com.aiuta.fashionsdk.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.debug.DefaultAiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.internal.analytic.sendConfigurationEvent
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.internal.validation.features.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.theme.validateWithSettings
import com.aiuta.fashionsdk.configuration.ui.AiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic

@Immutable
public class AiutaConfiguration private constructor(
    public val aiuta: Aiuta,
    public val debugSettings: AiutaDebugSettings,
    public val features: AiutaFeatures,
    public val userInterface: AiutaUserInterfaceConfiguration,
) {
    public val aiutaAnalytic: InternalAiutaAnalytic by lazy { aiuta.internalAiutaAnalytic }

    @AiutaDsl
    public class Builder {
        public var aiuta: Aiuta? = null
        public var debugSettings: AiutaDebugSettings? = null
        public var features: AiutaFeatures? = null
        public var userInterface: AiutaUserInterfaceConfiguration? = null

        public fun build(): AiutaConfiguration {
            val parentClass = "AiutaTryOnConfiguration"

            val innerDebugSettings = debugSettings ?: DefaultAiutaDebugSettings

            val internalAiuta = aiuta.checkNotNullWithDescription(
                parentClass = parentClass,
                property = "aiuta",
            )
            val internalFeatures = features.checkNotNullWithDescription(
                parentClass = parentClass,
                property = "features",
            ).also { features ->
                features.validateWithSettings(
                    logger = internalAiuta.logger,
                    debugSettings = innerDebugSettings,
                )
            }
            val innerUserInterface = userInterface.checkNotNullWithDescription(
                parentClass = parentClass,
                property = "userInterface",
            ).also { configuration ->
                configuration.theme.validateWithSettings(
                    logger = internalAiuta.logger,
                    debugSettings = innerDebugSettings,
                )
            }

            return AiutaConfiguration(
                aiuta = internalAiuta,
                debugSettings = innerDebugSettings,
                features = internalFeatures,
                userInterface = innerUserInterface,
            ).also {
                it.sendConfigurationEvent()
            }
        }
    }
}

public inline fun aiutaConfiguration(
    block: AiutaConfiguration.Builder.() -> Unit,
): AiutaConfiguration = AiutaConfiguration.Builder().apply(block).build()

@Composable
public fun rememberAiutaConfiguration(
    block: AiutaConfiguration.Builder.() -> Unit,
): AiutaConfiguration = remember { aiutaConfiguration(block) }
