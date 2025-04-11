package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

// For native we should always ask permission
internal actual fun shouldAskPermission(permission: AiutaPickerSource): Boolean = true
