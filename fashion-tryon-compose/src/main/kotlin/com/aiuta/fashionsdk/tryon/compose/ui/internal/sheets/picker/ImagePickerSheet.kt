package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker

import androidx.annotation.DrawableRes
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.domain.models.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker.analytic.sendSelectNewPhotosEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.CameraFileProvider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.openCameraPicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.openMultipleImagePicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.provideCameraPicker
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.provideMultipleImagePicker

@Composable
internal fun ColumnScope.ImagePickerSheet() {
    val context = LocalContext.current
    val controller = LocalController.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val sharedModifier = Modifier.fillMaxWidth().height(74.dp)

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

    val imagePickerLauncher =
        provideMultipleImagePicker { uris ->
            controller.sendSelectNewPhotosEvent(fromGallery = uris.size)
            controller.lastSavedImages.value =
                LastSavedImages.UriSource(
                    imageUris = uris.map { it.toString() },
                )
            controller.bottomSheetNavigator.hide()
        }

    SheetDivider()

    Spacer(Modifier.height(4.dp))

    PickerButton(
        modifier = sharedModifier,
        iconRes = FashionIcon.Camera24,
        text = stringResources.pickerSheetTakePhoto,
        onClick = {
            newImageUri?.let { uri ->
                openCameraPicker(
                    newImageUri = { uri },
                    getLauncher = { cameraPickerLauncher },
                )
            }
        },
    )

    PickerButton(
        modifier = sharedModifier,
        iconRes = FashionIcon.Gallery24,
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
    @DrawableRes
    iconRes: Int,
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
            imageVector = ImageVector.vectorResource(iconRes),
            contentDescription = null,
            tint = theme.colors.brand,
        )

        Spacer(Modifier.width(16.dp))

        Box(
            modifier = Modifier.weight(1f).fillMaxHeight(),
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = text,
                style = MaterialTheme.typography.body1,
                color = theme.colors.primary,
            )

            if (shouldDrawDivider) {
                Divider(
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                    color = theme.colors.gray1,
                )
            }
        }
    }
}
