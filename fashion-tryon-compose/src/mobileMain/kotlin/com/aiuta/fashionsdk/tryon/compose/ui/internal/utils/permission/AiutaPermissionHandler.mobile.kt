package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.exception.AiutaDeniedAlwaysException
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.utils.toAiuta
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.utils.toMoko
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.PermissionsController

internal actual class AiutaPermissionHandler(
    public val permissionsController: PermissionsController,
) {
    public actual suspend fun providePermission(pickerSource: AiutaPickerSource) {
        try {
            permissionsController.providePermission(pickerSource.toMoko())
        } catch (e: DeniedAlwaysException) {
            throw AiutaDeniedAlwaysException()
        }
    }

    public actual suspend fun isPermissionGranted(pickerSource: AiutaPickerSource): Boolean = permissionsController.isPermissionGranted(pickerSource.toMoko())

    public actual fun openAppSettings() {
        permissionsController.openAppSettings()
    }

    public actual suspend fun getPermissionState(pickerSource: AiutaPickerSource): AiutaPermissionState = permissionsController.getPermissionState(pickerSource.toMoko()).toAiuta()
}
