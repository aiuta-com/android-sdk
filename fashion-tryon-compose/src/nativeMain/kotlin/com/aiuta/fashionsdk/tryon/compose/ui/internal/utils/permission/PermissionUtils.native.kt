package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import dev.icerock.moko.permissions.Permission

// For native we should always ask permission
internal actual fun shouldAskPermission(permission: Permission): Boolean {
    return true
}
