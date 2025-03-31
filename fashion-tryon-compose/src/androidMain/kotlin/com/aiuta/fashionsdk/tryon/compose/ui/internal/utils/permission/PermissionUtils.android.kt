package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.gallery.GALLERY

// In Android we don't need to ask GALLERY permission
private val notRequestedPermission by lazy { listOf(Permission.GALLERY) }

internal actual fun shouldAskPermission(permission: Permission): Boolean {
    return permission !in notRequestedPermission
}
