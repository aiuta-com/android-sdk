package com.aiuta.fashionsdk.tryon.compose.defaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.AiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.aiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.color.AiutaColorTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.theme
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.button.defaultButton
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.error.defaultErrorSnackbar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.image.defaultImage
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.label.defaultLabel
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.pagebar.defaultPageBar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.productbar.defaultProductBar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.selection.defaultSelectionSnackbar
import com.aiuta.fashionsdk.tryon.compose.defaults.theme.sheet.defaultBottomSheet

public fun defaultAiutaUserInterfaceConfiguration(
    actions: AiutaUserInterfaceActions,
): AiutaUserInterfaceConfiguration = aiutaUserInterfaceConfiguration {
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

@Composable
public fun rememberDefaultAiutaUserInterfaceConfiguration(
    actions: AiutaUserInterfaceActions,
): AiutaUserInterfaceConfiguration = remember { defaultAiutaUserInterfaceConfiguration(actions) }
