package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import androidx.compose.runtime.Composable

@Composable
internal actual fun rememberAiutaPermissionHandler(): AiutaPermissionHandler = AiutaPermissionHandler()

internal actual class AiutaPermissionHandler {
    actual suspend fun providePermission(pickerSource: AiutaPickerSource) {
        // Ignore
    }

    actual suspend fun isPermissionGranted(pickerSource: AiutaPickerSource): Boolean = true

    actual suspend fun getPermissionState(pickerSource: AiutaPickerSource): AiutaPermissionState = AiutaPermissionState.GRANTED

    actual fun openAppSettings() {
        error("Not supported opening settings on this platform")
    }
}
