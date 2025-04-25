package com.aiuta.fashionsdk.tryon.compose.defaults.theme

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.configuration.ui.aiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.configuration.ui.theme.color.AiutaColorTheme
import com.aiuta.fashionsdk.configuration.ui.theme.theme
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.button.defaultButton
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.error.defaultErrorSnackbar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.image.defaultImage
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.label.defaultLabel
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.pagebar.defaultPageBar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.productbar.defaultProductBar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.selection.defaultSelectionSnackbar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.sheet.defaultBottomSheet

public fun AiutaConfiguration.Builder.defaultAiutaUserInterfaceConfiguration(
    actions: AiutaUserInterfaceActions,
): AiutaConfiguration.Builder = apply {
    aiutaUserInterfaceConfiguration {
        this.actions = actions
        theme {
            color = AiutaColorTheme.Default()

            defaultButton()
            defaultErrorSnackbar()
            defaultImage()
            defaultLabel()
            defaultPageBar()
            defaultProductBar()
            defaultSelectionSnackbar()
            defaultBottomSheet()
        }
    }
}
