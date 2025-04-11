package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

// For desktop we shouldn't ask permission
internal actual fun shouldAskPermission(permission: AiutaPickerSource): Boolean = false
