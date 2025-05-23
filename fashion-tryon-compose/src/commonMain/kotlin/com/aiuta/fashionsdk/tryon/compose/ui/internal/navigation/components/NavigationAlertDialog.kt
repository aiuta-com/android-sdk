package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.hideDialog
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButton
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonSizes
import com.aiuta.fashionsdk.tryon.compose.uikit.button.FashionButtonStyles
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun NavigationAlertDialog(
    modifier: Modifier = Modifier,
    state: AiutaTryOnDialogState,
) {
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current

    val sharedModifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

    AlertDialog(
        modifier = modifier.padding(horizontal = 16.dp),
        onDismissRequest = dialogController::hideDialog,
        backgroundColor = theme.color.background,
        contentColor = theme.color.primary,
        shape = RoundedCornerShape(32.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title =
        state.title?.let {
            {
                Column(
                    modifier = sharedModifier,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(Modifier.height(24.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = state.title,
                        style = theme.label.typography.titleL,
                        color = theme.color.primary,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.description,
                style = theme.label.typography.regular,
                color = theme.color.secondary,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        buttons = {
            Column(
                modifier = sharedModifier,
                verticalArrangement = Arrangement.Center,
            ) {
                FashionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.confirmButton,
                    style = FashionButtonStyles.primaryStyle(theme),
                    size = FashionButtonSizes.lSize(),
                    onClick = {
                        state.onConfirm.invoke()
                        dialogController.hideDialog()
                    },
                )

                state.onDismiss?.let {
                    Spacer(Modifier.height(8.dp))

                    FashionButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = theme.selectionSnackbar.strings.cancel,
                        style = FashionButtonStyles.secondaryStyle(theme),
                        size = FashionButtonSizes.lSize(),
                        onClick = state.onDismiss,
                    )
                }

                Spacer(Modifier.height(24.dp))
            }
        },
    )
}
