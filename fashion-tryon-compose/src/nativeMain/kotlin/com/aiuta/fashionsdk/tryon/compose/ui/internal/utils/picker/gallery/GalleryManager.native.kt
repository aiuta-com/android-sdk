package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.picker.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.aiuta.fashionsdk.tryon.core.domain.models.image.AiutaPlatformImage
import platform.UIKit.UIApplication
import platform.UIKit.UIImage
import platform.UIKit.UIImagePickerController
import platform.UIKit.UIImagePickerControllerDelegateProtocol
import platform.UIKit.UIImagePickerControllerOriginalImage
import platform.UIKit.UIImagePickerControllerSourceType
import platform.UIKit.UINavigationControllerDelegateProtocol
import platform.darwin.NSObject

@Composable
internal actual fun rememberGalleryManager(
    onResult: (AiutaPlatformImage?) -> Unit,
): GalleryManager {
    val imagePicker = UIImagePickerController()
    val galleryDelegate =
        remember {
            object :
                NSObject(),
                UIImagePickerControllerDelegateProtocol,
                UINavigationControllerDelegateProtocol {
                override fun imagePickerController(
                    picker: UIImagePickerController,
                    didFinishPickingMediaWithInfo: Map<Any?, *>,
                ) {
                    val image =
                        didFinishPickingMediaWithInfo.getValue(
                            UIImagePickerControllerOriginalImage,
                        ) as? UIImage

                    image?.let { onResult.invoke(AiutaPlatformImage(image)) }
                    picker.dismissViewControllerAnimated(true, null)
                }
            }
        }

    return remember {
        GalleryManager {
            imagePicker.setSourceType(
                UIImagePickerControllerSourceType.UIImagePickerControllerSourceTypePhotoLibrary,
            )
            imagePicker.setAllowsEditing(false)
            imagePicker.setDelegate(galleryDelegate)
            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                imagePicker,
                true,
                null,
            )
        }
    }
}

internal actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}
