package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.utils

import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.AiutaPermissionState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.AiutaPickerSource
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.gallery.GALLERY

internal fun AiutaPickerSource.toMoko(): Permission = when (this) {
    AiutaPickerSource.CAMERA -> Permission.CAMERA
    AiutaPickerSource.GALLERY -> Permission.GALLERY
}

internal fun PermissionState.toAiuta(): AiutaPermissionState = when (this) {
    PermissionState.NotDetermined -> AiutaPermissionState.NOT_DETERMINED
    PermissionState.NotGranted -> AiutaPermissionState.NOT_GRANTED
    PermissionState.Granted -> AiutaPermissionState.GRANTED
    PermissionState.Denied -> AiutaPermissionState.DENIED
    PermissionState.DeniedAlways -> AiutaPermissionState.DENIED_ALWAYS
}

internal fun AiutaPermissionState.toMoko(): PermissionState = when (this) {
    AiutaPermissionState.NOT_DETERMINED -> PermissionState.NotDetermined
    AiutaPermissionState.NOT_GRANTED -> PermissionState.NotGranted
    AiutaPermissionState.GRANTED -> PermissionState.Granted
    AiutaPermissionState.DENIED -> PermissionState.Denied
    AiutaPermissionState.DENIED_ALWAYS -> PermissionState.DeniedAlways
}
