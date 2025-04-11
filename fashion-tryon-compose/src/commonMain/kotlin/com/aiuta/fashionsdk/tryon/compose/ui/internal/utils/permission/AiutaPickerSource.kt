package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission

internal enum class AiutaPickerSource {
    CAMERA,

    GALLERY,
}

internal enum class AiutaPermissionState {
    NOT_DETERMINED,

    NOT_GRANTED,

    GRANTED,

    DENIED,

    DENIED_ALWAYS,
}
