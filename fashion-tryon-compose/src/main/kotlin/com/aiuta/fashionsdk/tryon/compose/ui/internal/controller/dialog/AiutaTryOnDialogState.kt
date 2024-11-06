package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog

import androidx.compose.runtime.Immutable

@Immutable
internal class AiutaTryOnDialogState(
    val title: String? = null,
    val description: String,
    val confirmButton: String,
    val onConfirm: () -> Unit,
    val onDismiss: (() -> Unit)? = null,
)
