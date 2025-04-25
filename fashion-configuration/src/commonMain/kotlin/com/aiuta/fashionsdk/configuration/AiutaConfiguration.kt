package com.aiuta.fashionsdk.configuration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.configuration.debug.AiutaDebugSettings
import com.aiuta.fashionsdk.configuration.debug.DefaultAiutaDebugSettings
import com.aiuta.fashionsdk.configuration.features.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.configuration.internal.analytic.sendConfigurationEvent
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.internal.validation.features.validateWithSettings
import com.aiuta.fashionsdk.configuration.internal.validation.theme.validateWithSettings
import com.aiuta.fashionsdk.configuration.ui.AiutaUserInterfaceConfiguration

@Immutable
public class AiutaConfiguration private constructor(
    public val userInterface: AiutaUserInterfaceConfiguration,
    public val tryOnConfiguration: AiutaTryOnConfiguration,
    public val debugSettings: AiutaDebugSettings,
) {
    @AiutaDsl
    public class Builder {
        public var userInterface: AiutaUserInterfaceConfiguration? = null
        public var tryOnConfiguration: AiutaTryOnConfiguration? = null
        public var debugSettings: AiutaDebugSettings? = null

        public fun build(): AiutaConfiguration {
            val parentClass = "AiutaTryOnConfiguration"

            val innerDebugSettings = debugSettings ?: DefaultAiutaDebugSettings

            val innerTryOnConfiguration = tryOnConfiguration.checkNotNullWithDescription(
                parentClass = parentClass,
                property = "tryOnConfiguration",
            ).also { configuration ->
                configuration.features.validateWithSettings(
                    logger = configuration.aiuta.logger,
                    debugSettings = innerDebugSettings,
                )
            }
            val innerUserInterface = userInterface.checkNotNullWithDescription(
                parentClass = parentClass,
                property = "userInterface",
            ).also { configuration ->
                configuration.theme.validateWithSettings(
                    logger = innerTryOnConfiguration.aiuta.logger,
                    debugSettings = innerDebugSettings,
                )
            }

            return AiutaConfiguration(
                userInterface = innerUserInterface,
                tryOnConfiguration = innerTryOnConfiguration,
                debugSettings = innerDebugSettings,
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
