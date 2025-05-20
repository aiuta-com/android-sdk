package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.utils

import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.AiutaPermissionState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
internal fun PermissionState.toAiutaState(): AiutaPermissionState = when {
    status.isGranted -> AiutaPermissionState.GRANTED
    !status.shouldShowRationale -> AiutaPermissionState.DENIED
    else -> AiutaPermissionState.DENIED_ALWAYS
}
