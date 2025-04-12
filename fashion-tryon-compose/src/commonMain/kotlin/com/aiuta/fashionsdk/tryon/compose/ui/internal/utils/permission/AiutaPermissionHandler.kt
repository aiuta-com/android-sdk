package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import androidx.compose.runtime.Composable

@Composable
internal expect fun rememberAiutaPermissionHandler(): AiutaPermissionHandler

internal expect class AiutaPermissionHandler {

    suspend fun providePermission(pickerSource: AiutaPickerSource)

    suspend fun isPermissionGranted(pickerSource: AiutaPickerSource): Boolean

    suspend fun getPermissionState(pickerSource: AiutaPickerSource): AiutaPermissionState

    fun openAppSettings()
}
