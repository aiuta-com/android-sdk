package com.aiuta.fashionsdk.tryon.compose.configuration.ui

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.meta.AiutaStyleMetaData
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme

@Immutable
public class AiutaUserInterfaceConfiguration private constructor(
    public val styleMetaData: AiutaStyleMetaData?,
    public val actions: AiutaUserInterfaceActions,
    public val theme: AiutaTheme,
) {
    public class Builder {
        public var styleMetaData: AiutaStyleMetaData? = null
        public var actions: AiutaUserInterfaceActions? = null
        public var theme: AiutaTheme? = null

        public fun build(): AiutaUserInterfaceConfiguration {
            val parentClass = "AiutaUserInterfaceConfiguration"

            return AiutaUserInterfaceConfiguration(
                styleMetaData = styleMetaData,
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

public inline fun aiutaUserInterfaceConfiguration(
    block: AiutaUserInterfaceConfiguration.Builder.() -> Unit,
): AiutaUserInterfaceConfiguration = AiutaUserInterfaceConfiguration.Builder().apply(block).build()
