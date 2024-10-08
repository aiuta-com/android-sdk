package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.showDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker.analytic.sendSelectNewPhotosEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.CameraFileProvider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.openCameraPicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.openMultipleImagePicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.openSettings
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.provideCameraPicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.provideSingleImagePicker
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun ColumnScope.ImagePickerSheet() {
    val context = LocalContext.current
    val controller = LocalController.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val sharedModifier =
        Modifier
            .fillMaxWidth()
            .height(74.dp)

    val newImageUri = remember { CameraFileProvider.newImageUri(context = context) }
    val cameraPickerLauncher =
        provideCameraPicker { hasImage ->
            if (hasImage && newImageUri != null) {
                controller.sendSelectNewPhotosEvent(fromCamera = 1)
                controller.lastSavedImages.value =
                    LastSavedImages.UriSource(
                        imageUris = listOf(newImageUri.toString()),
                    )
                controller.bottomSheetNavigator.hide()
            }
        }
    val startCameraPickerFlow = {
        newImageUri?.let { uri ->
            openCameraPicker(
                newImageUri = { uri },
                getLauncher = { cameraPickerLauncher },
            )
        }
    }
    val cameraPermissionState =
        rememberPermissionState(Manifest.permission.CAMERA) { isGranted ->
            // After granting permission, let's open camera
            if (isGranted) {
                startCameraPickerFlow()
            }
        }

    val imagePickerLauncher =
        provideSingleImagePicker { uri ->
            uri?.let {
                controller.sendSelectNewPhotosEvent(fromGallery = 1)
                controller.lastSavedImages.value =
                    LastSavedImages.UriSource(
                        imageUris = listOf(uri.toString()),
                    )
                controller.bottomSheetNavigator.hide()
            }
        }

    SheetDivider()

    Spacer(Modifier.height(4.dp))

    PickerButton(
        modifier = sharedModifier,
        iconPainter = rememberAsyncImagePainter(theme.icons.takePhoto24.resource),
        text = stringResources.pickerSheetTakePhoto,
        onClick = {
            if (cameraPermissionState.status.isGranted) {
                // Permission granted, let's open camera
                startCameraPickerFlow()
            } else {
                if (cameraPermissionState.status.shouldShowRationale) {
                    controller.bottomSheetNavigator.hide()
                    dialogController.showDialog(
                        dialogState =
                            AiutaTryOnDialogState(
                                title = stringResources.dialogCameraPermissionTitle,
                                description = stringResources.dialogCameraPermissionDescription,
                                onConfirm = context::openSettings,
                            ),
                    )
                } else {
                    cameraPermissionState.launchPermissionRequest()
                }
            }
        },
    )

    PickerButton(
        modifier = sharedModifier,
        iconPainter = rememberAsyncImagePainter(theme.icons.photoLibrary24.resource),
        text = stringResources.pickerSheetChooseLibrary,
        shouldDrawDivider = false,
        onClick = {
            openMultipleImagePicker { imagePickerLauncher }
        },
    )
}

@Composable
private fun PickerButton(
    modifier: Modifier = Modifier,
    iconPainter: Painter,
    text: String,
    shouldDrawDivider: Boolean = true,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current

    Row(
        modifier =
            modifier
                .clickableUnindicated {
                    onClick()
                }
                .padding(
                    horizontal = 16.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = iconPainter,
            contentDescription = null,
            tint = theme.colors.brand,
        )

        Spacer(Modifier.width(16.dp))

        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxHeight(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = text,
                style = theme.typography.cells,
                color = theme.colors.primary,
            )

            if (shouldDrawDivider) {
                Divider(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                    color = theme.colors.neutral,
                )
            }
        }
    }
}
