package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun CoroutineScope.actionWithPermission(
    permission: Permission,
    permissionsController: PermissionsController,
    onGranted: suspend () -> Unit,
    onAlwaysDenied: suspend () -> Unit,
) {
    launch {
        when {
            !shouldAskPermission(permission) -> onGranted()

            permissionsController.isPermissionGranted(permission) -> onGranted()

            else -> {
                try {
                    val permissionState = permissionsController.getPermissionState(permission)

                    println("actionWithPermission(): permissionState - $permissionState")

                    if (permissionState == PermissionState.DeniedAlways) {
                        onAlwaysDenied()
                    } else {
                        permissionsController.providePermission(permission)
                        // Permission granted, let's do main action
                        onGranted()
                    }
                } catch (e: DeniedAlwaysException) {
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

internal expect fun shouldAskPermission(permission: Permission): Boolean
