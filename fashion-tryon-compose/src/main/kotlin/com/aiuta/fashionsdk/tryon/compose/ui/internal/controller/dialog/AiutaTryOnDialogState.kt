package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog

import androidx.compose.runtime.Immutable

@Immutable
internal class AiutaTryOnDialogState(
    val title: String,
    val description: String,
    val onConfirm: () -> Unit,
    val onDismiss: (() -> Unit)? = null,
)
