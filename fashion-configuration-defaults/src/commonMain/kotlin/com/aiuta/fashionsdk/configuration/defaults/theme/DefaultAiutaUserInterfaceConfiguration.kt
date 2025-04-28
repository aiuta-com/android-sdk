package com.aiuta.fashionsdk.configuration.defaults.theme

import com.aiuta.fashionsdk.configuration.AiutaConfiguration
import com.aiuta.fashionsdk.configuration.defaults.theme.button.defaultButton
import com.aiuta.fashionsdk.configuration.defaults.theme.error.defaultErrorSnackbar
import com.aiuta.fashionsdk.configuration.defaults.theme.image.defaultImage
import com.aiuta.fashionsdk.configuration.defaults.theme.label.defaultLabel
import com.aiuta.fashionsdk.configuration.defaults.theme.pagebar.defaultPageBar
import com.aiuta.fashionsdk.configuration.defaults.theme.powerby.defaultPoweredBar
import com.aiuta.fashionsdk.configuration.defaults.theme.productbar.defaultProductBar
import com.aiuta.fashionsdk.configuration.defaults.theme.selection.defaultSelectionSnackbar
import com.aiuta.fashionsdk.configuration.defaults.theme.sheet.defaultBottomSheet
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.configuration.ui.aiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.configuration.ui.theme.color.AiutaColorTheme
import com.aiuta.fashionsdk.configuration.ui.theme.theme

public fun AiutaConfiguration.Builder.defaultAiutaUserInterfaceConfiguration(
    actions: AiutaUserInterfaceActions,
): AiutaConfiguration.Builder = aiutaUserInterfaceConfiguration {
    this.actions = actions
    theme {
        color = AiutaColorTheme.Default()

        defaultButton()
        defaultErrorSnackbar()
        defaultImage()
        defaultLabel()
        defaultPageBar()
        defaultProductBar()
        defaultPoweredBar()
        defaultSelectionSnackbar()
        defaultBottomSheet()
    }
}
