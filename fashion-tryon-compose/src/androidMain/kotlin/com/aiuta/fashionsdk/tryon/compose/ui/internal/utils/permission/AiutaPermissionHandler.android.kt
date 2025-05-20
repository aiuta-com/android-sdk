package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import androidx.activity.compose.LocalActivityResultRegistryOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.LocalLifecycleOwner

@Composable
internal actual fun rememberAiutaPermissionHandler(): AiutaPermissionHandler {
    val permissionsController = rememberAiutaPermissionsController()

    val activityResultRegistry = LocalActivityResultRegistryOwner.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    LaunchedEffect(
        activityResultRegistry,
        lifecycleOwner,
        context,
    ) {
        permissionsController.bind(
            resultRegistry = checkNotNull(activityResultRegistry),
        )
    }

    return remember {
        AiutaPermissionHandler(
            permissionsController = permissionsController,
        )
    }
}
