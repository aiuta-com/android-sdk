package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
internal actual fun rememberAiutaPermissionHandler(): AiutaPermissionHandler {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionsController: PermissionsController = remember(factory) {
        factory.createPermissionsController()
    }

    return AiutaPermissionHandler(
        permissionsController = permissionsController,
    )
}
