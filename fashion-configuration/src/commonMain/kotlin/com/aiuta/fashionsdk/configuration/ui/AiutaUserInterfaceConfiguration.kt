package com.aiuta.fashionsdk.configuration.ui

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme

@Immutable
public class AiutaUserInterfaceConfiguration(
    public val actions: AiutaUserInterfaceActions,
    public val theme: AiutaTheme,
) {
    public class Builder {
        public var actions: AiutaUserInterfaceActions? = null
        public var theme: AiutaTheme? = null

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

public fun AiutaConfiguration.Builder.aiutaUserInterfaceConfiguration(
    block: AiutaUserInterfaceConfiguration.Builder.() -> Unit,
): AiutaConfiguration.Builder = apply {
    userInterface = AiutaUserInterfaceConfiguration.Builder().apply(block).build()
}
