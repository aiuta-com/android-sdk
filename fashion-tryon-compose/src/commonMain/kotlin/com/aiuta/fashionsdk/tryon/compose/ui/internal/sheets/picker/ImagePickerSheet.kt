package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.icon.AiutaIcons
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsPickerEventType
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.LastSavedImages
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPickerAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateAutoTryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDialogController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.AiutaTryOnDialogState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.hideDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.dialog.showDialog
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationBottomSheetScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.actionWithPermission
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.camera.rememberCameraManager
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.gallery.rememberGalleryManager
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.permissions.gallery.GALLERY
import kotlinx.coroutines.launch

@Composable
internal fun ColumnScope.ImagePickerSheet(pickerData: NavigationBottomSheetScreen.ImagePicker) {
    val controller = LocalController.current
    val dialogController = LocalAiutaTryOnDialogController.current
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    val sharedModifier =
        Modifier
            .fillMaxWidth()
            .height(74.dp)

    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionsController: PermissionsController =
        remember(factory) {
            factory.createPermissionsController()
        }
    val scope = rememberCoroutineScope()

    BindEffect(permissionsController)

    val cameraManager =
        rememberCameraManager { image ->
            image?.let {
                controller.sendPickerAnalytic(
                    event = AiutaAnalyticsPickerEventType.NEW_PHOTO_TAKEN,
                    pageId = pickerData.originPageId,
                )
                controller.lastSavedImages.value =
                    LastSavedImages.PlatformImageSource(
                        platformImages = listOf(image),
                    )
                // Activate try on
                controller.activateAutoTryOn()
                // Move back
                controller.bottomSheetNavigator.hide()
            }
        }

    val galleryManager =
        rememberGalleryManager { image ->
            image?.let {
                controller.sendPickerAnalytic(
                    event = AiutaAnalyticsPickerEventType.GALLERY_PHOTO_SELECTED,
                    pageId = pickerData.originPageId,
                )
                controller.lastSavedImages.value =
                    LastSavedImages.PlatformImageSource(
                        platformImages = listOf(image),
                    )
                // Activate try on
                controller.activateAutoTryOn()
                // Move back
                controller.bottomSheetNavigator.hide()
            }
        }

    SheetDivider()

    Spacer(Modifier.height(4.dp))

    PickerButton(
        modifier = sharedModifier,
        icon = theme.icons.takePhoto24,
        text = stringResources.pickerSheetTakePhoto,
        onClick = {
            scope.actionWithPermission(
                permission = Permission.CAMERA,
                permissionsController = permissionsController,
                onGranted = {
                    cameraManager.launch()
                },
                onAlwaysDenied = {
                    controller.bottomSheetNavigator.hide()
                    dialogController.showDialog(
                        dialogState =
                            AiutaTryOnDialogState(
                                title = stringResources.dialogCameraPermissionTitle,
                                description = stringResources.dialogCameraPermissionDescription,
                                confirmButton = stringResources.dialogCameraPermissionConfirmButton,
                                onConfirm = permissionsController::openAppSettings,
                                onDismiss = dialogController::hideDialog,
                            ),
                    )
                },
            )
        },
    )

    PickerButton(
        modifier = sharedModifier,
        icon = theme.icons.photoLibrary24,
        text = stringResources.pickerSheetChooseLibrary,
        onClick = {
            controller.sendPickerAnalytic(
                event = AiutaAnalyticsPickerEventType.PHOTO_GALLERY_OPENED,
                pageId = pickerData.originPageId,
            )
            scope.actionWithPermission(
                permission = Permission.GALLERY,
                permissionsController = permissionsController,
                onGranted = {
                    galleryManager.launch()
                },
                onAlwaysDenied = {
                    // Show nothing
                    controller.bottomSheetNavigator.hide()
                },
            )
        },
    )

    PickerButton(
        modifier = sharedModifier,
        icon = theme.icons.selectModel24,
        text = stringResources.modelSelect,
        shouldDrawDivider = false,
        onClick = {
            controller.bottomSheetNavigator.hide()
            controller.navigateTo(NavigationScreen.ModelSelector)
        },
    )
}

@Composable
private fun PickerButton(
    modifier: Modifier = Modifier,
    icon: AiutaIcons.AiutaIcon,
    text: String,
    shouldDrawDivider: Boolean = true,
    onClick: () -> Unit,
) {
    val theme = LocalTheme.current
    val rawModifier = modifier.clickableUnindicated { onClick() }
    val finalModifier =
        if (theme.toggles.isDelimitersExtended) {
            rawModifier.padding(start = 16.dp)
        } else {
            rawModifier.padding(horizontal = 16.dp)
        }

    Row(
        modifier = finalModifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AiutaIcon(
            modifier = Modifier.size(24.dp),
            icon = icon,
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
