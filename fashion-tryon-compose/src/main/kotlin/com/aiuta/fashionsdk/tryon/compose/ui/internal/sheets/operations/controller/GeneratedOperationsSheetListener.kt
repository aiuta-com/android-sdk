package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

@Composable
internal fun GeneratedOperationsSheetListener() {
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        controller.generatedOperationInteractor
            .countGeneratedOperation()
            .collect { countGeneratedOperation ->
                if (countGeneratedOperation == 0) {
                    controller.lastSavedPhotoUris.value = emptyList()
                    controller.bottomSheetNavigator.hide()
                }
            }
    }
}
