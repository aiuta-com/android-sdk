package com.aiuta.fashionsdk.tryon.compose.domain.internal.share

import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils.generateImageFileName
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import objcnames.classes.LPLinkMetadata as ObjLPLinkMetadata
import platform.CoreGraphics.CGSize
import platform.Foundation.NSItemProvider
import platform.Foundation.NSOperatingSystemVersion
import platform.Foundation.NSProcessInfo
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.writeToFile
import platform.LinkPresentation.LPLinkMetadata
import platform.UIKit.UIActivityItemSourceProtocol
import platform.UIKit.UIActivityType
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIImage
import platform.UIKit.UIImageJPEGRepresentation
import platform.darwin.NSObject

internal class ShareableImage(
    private val image: UIImage,
    private val imageTitle: String? = null,
) : NSObject(),
    UIActivityItemSourceProtocol {

    override fun activityViewControllerPlaceholderItem(
        activityViewController: UIActivityViewController,
    ): Any = UIImage()

    @OptIn(ExperimentalForeignApi::class)
    override fun activityViewController(
        activityViewController: UIActivityViewController,
        thumbnailImageForActivityType: String?,
        suggestedSize: CValue<CGSize>,
    ): UIImage = image

    override fun activityViewController(
        activityViewController: UIActivityViewController,
        itemForActivityType: UIActivityType?,
    ): Any? {
        // Instead of sharing the UIImage, share the file URL to ensure JPEG format is preserved
        return saveImageToTemporaryLocation(image) ?: UIImage()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun activityViewControllerLinkMetadata(activityViewController: UIActivityViewController): ObjLPLinkMetadata? {
        val ios13Version = cValue<NSOperatingSystemVersion> {
            majorVersion = 13
            minorVersion = 0
            patchVersion = 0
        }
        return if (NSProcessInfo.processInfo.isOperatingSystemAtLeastVersion(ios13Version)) {
            LPLinkMetadata().apply {
                saveImageToTemporaryLocation(image)?.let { tempFileUrl ->
                    val provider = NSItemProvider(tempFileUrl)
                    imageProvider = provider
                    iconProvider = provider
                }
                title = imageTitle ?: "Image"
            } as ObjLPLinkMetadata
        } else {
            null
        }
    }

    private fun saveImageToTemporaryLocation(image: UIImage): NSURL? {
        // Save the UIImage as PNG data
        val imageData = UIImageJPEGRepresentation(image, 1.0)
        // The system may delete files in this directory when it needs to reclaim
        // disk space or when the app is terminated.
        val tempDirectory = NSTemporaryDirectory()
        // Generate a unique filename using UUID
        val uniqueFilename = generateImageFileName()
        val tempFilePath = tempDirectory + uniqueFilename
        val success = imageData?.writeToFile(tempFilePath, atomically = true) ?: false

        return if (success) NSURL.fileURLWithPath(tempFilePath) else null
    }
}

internal class ShareableAddition(
    private val addition: Any,
) : NSObject(),
    UIActivityItemSourceProtocol {

    override fun activityViewController(
        activityViewController: UIActivityViewController,
        itemForActivityType: UIActivityType?,
    ): Any? = when (itemForActivityType) {
        null -> null
        "com.burbn.instagram.shareextension", "com.tinyspeck.chatlyio.share" -> null
        else -> addition
    }

    override fun activityViewControllerPlaceholderItem(
        activityViewController: UIActivityViewController,
    ): Any = addition
}
