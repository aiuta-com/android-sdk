package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

internal fun AiutaTryOnDialogController.showDialog(dialogState: AiutaTryOnDialogState) {
    state.value = dialogState
}

internal fun AiutaTryOnDialogController.hideDialog() {
    state.value = null
}

@Composable
internal fun AiutaTryOnDialogController.isDialogVisible(): State<Boolean> = remember(state.value) {
    derivedStateOf {
        state.value != null
    }
}
