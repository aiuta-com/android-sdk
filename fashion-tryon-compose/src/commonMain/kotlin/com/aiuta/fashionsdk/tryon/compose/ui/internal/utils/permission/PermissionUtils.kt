package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.exception.AiutaDeniedAlwaysException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun CoroutineScope.actionWithPermission(
    pickerSource: AiutaPickerSource,
    permissionHandler: AiutaPermissionHandler,
    onGranted: suspend () -> Unit,
    onAlwaysDenied: suspend () -> Unit,
) {
    launch {
        when {
            !shouldAskPermission(pickerSource) -> onGranted()

            permissionHandler.isPermissionGranted(pickerSource) -> onGranted()

            else -> {
                try {
                    val permissionState = permissionHandler.getPermissionState(pickerSource)

                    println("actionWithPermission(): permissionState - $permissionState")

                    if (permissionState == AiutaPermissionState.DENIED_ALWAYS) {
                        onAlwaysDenied()
                    } else {
                        permissionHandler.providePermission(pickerSource)
                        // Permission granted, let's do main action
                        onGranted()
                    }
                } catch (e: AiutaDeniedAlwaysException) {
                    println("actionWithPermission(): DeniedAlwaysException exception")
                    onAlwaysDenied()
                } catch (e: Exception) {
                    println("actionWithPermission(): general exception - $e")
                    // Just intercept
                }
            }
        }
    }
}

internal expect fun shouldAskPermission(permission: AiutaPickerSource): Boolean

internal expect fun isPickerSourceAvailable(permission: AiutaPickerSource): Boolean
