package com.aiuta.fashionsdk.tryon.compose.defaults.theme.sheet

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.bottomSheet
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.grabber.AiutaBottomSheetThemeGrabber
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.shapes.AiutaBottomSheetThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.toggles.AiutaBottomSheetThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.typography.AiutaBottomSheetThemeTypography

public fun AiutaTheme.Builder.defaultBottomSheet() {
    bottomSheet {
        typography = AiutaBottomSheetThemeTypography.Default()
        shapes = AiutaBottomSheetThemeShapes.Default()
        grabber = AiutaBottomSheetThemeGrabber.Default()
        toggles = AiutaBottomSheetThemeToggles.Default()
    }
}
