package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

// In Android we don't need to ask GALLERY permission
private val notRequestedPermission by lazy { listOf(AiutaPickerSource.GALLERY) }

internal actual fun shouldAskPermission(permission: AiutaPickerSource): Boolean = permission !in notRequestedPermission
