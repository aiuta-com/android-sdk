package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.hideDialog

@Composable
internal fun NavigationAlertDialog(
    modifier: Modifier = Modifier,
    state: AiutaTryOnDialogState,
) {
    val dialogController = LocalAiutaTryOnDialogController.current
    val stringResources = LocalAiutaTryOnStringResources.current
    val theme = LocalTheme.current

    val onDismissRequest = state.onDismiss ?: dialogController::hideDialog
    val sharedModifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)

    AlertDialog(
        modifier = modifier.padding(horizontal = 16.dp),
        onDismissRequest = onDismissRequest,
        backgroundColor = theme.colors.background,
        contentColor = theme.colors.primary,
        shape = RoundedCornerShape(32.dp),
        properties = DialogProperties(usePlatformDefaultWidth = false),
        title = {
            Column(
                modifier = sharedModifier,
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(Modifier.height(24.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = state.title,
                    style =
                        MaterialTheme.typography.h6.copy(
                            fontWeight = FontWeight.Bold,
                        ),
                    color = theme.colors.primary,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                )
            }
        },
        text = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = state.description,
                style = MaterialTheme.typography.body1,
                color = theme.colors.secondary,
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
                    text = stringResources.dialogCameraPermissionConfirmButton,
                    style = FashionButtonStyles.primaryStyle(theme),
                    size = FashionButtonSizes.lSize(),
                    onClick = {
                        state.onConfirm.invoke()
                        dialogController.hideDialog()
                    },
                )

                Spacer(Modifier.height(8.dp))

                FashionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResources.cancel,
                    style = FashionButtonStyles.outlineStyle(theme),
                    size = FashionButtonSizes.lSize(),
                    onClick = onDismissRequest,
                )

                Spacer(Modifier.height(24.dp))
            }
        },
    )
}
