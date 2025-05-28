package com.aiuta.fashionsdk.configuration.ui

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme

/**
 * User interface configuration for the Aiuta SDK.
 *
 * This immutable class contains all UI-related configuration including theming
 * and user interaction handling. It provides a centralized way to customize
 * the visual appearance and behavior of the SDK's user interface.
 *
 *
 * @property actions User interface action handlers for SDK interactions
 * @property theme Visual theme configuration for all UI components
 * @see AiutaUserInterfaceActions
 * @see AiutaTheme
 */
@Immutable
public class AiutaUserInterfaceConfiguration(
    public val actions: AiutaUserInterfaceActions,
    public val theme: AiutaTheme,
) {
    /**
     * Builder class for creating [AiutaUserInterfaceConfiguration] instances.
     *
     * This builder ensures all required UI configuration properties are set
     * before creating the final configuration instance.
     */
    public class Builder {
        /**
         * User interface action handlers.
         */
        public var actions: AiutaUserInterfaceActions? = null

        /**
         * Visual theme configuration.
         */
        public var theme: AiutaTheme? = null

        /**
         * Creates an [AiutaUserInterfaceConfiguration] instance with the configured properties.
         *
         * @return Configured [AiutaUserInterfaceConfiguration] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): AiutaUserInterfaceConfiguration {
            val parentClass = "AiutaUserInterfaceConfiguration"

            return AiutaUserInterfaceConfiguration(
                actions = actions.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "actions",
                ),
                theme = theme.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "theme",
                ),
            )
        }
    }
}

/**
 * Extension function for configuring UI within an [AiutaConfiguration.Builder].
 *
 * This extension provides a convenient DSL for configuring the user interface
 * as part of the main configuration setup.
 *
 * ```kotlin
 * val configuration = aiutaConfiguration {
 *     aiuta = ... // Aiuta instance
 
 *     features { /* ... */ }
 *
 *     userInterface {
 *         theme { /* ... */ }
 *
 *         actions = ... // AiutaUserInterfaceActions instance
 *     }
 * }
 * ```
 *
 * @param block Configuration block for setting up the UI configuration
 * @return The configuration builder for method chaining
 * @see AiutaConfiguration.Builder
 * @see AiutaUserInterfaceConfiguration.Builder
 */
public fun AiutaConfiguration.Builder.userInterface(
    block: AiutaUserInterfaceConfiguration.Builder.() -> Unit,
): AiutaConfiguration.Builder = apply {
    userInterface = AiutaUserInterfaceConfiguration.Builder().apply(block).build()
}
