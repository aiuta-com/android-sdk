package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

internal fun Context.getUriFromBitmap(
    bmp: Bitmap,
    filePrefix: String = "image",
    qualityPercent: Int = 100,
    isCache: Boolean,
): Uri? {
    var bmpUri: Uri? = null // Initialize the Uri object to null.
    try {
        val fileName = "${filePrefix}_${System.currentTimeMillis()}.png"
        // Create a new file with a unique name in the Pictures directory of the app's external files directory.
        val file =
            File(
                if (isCache) cacheDir else getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                fileName,
            )
        // Create a FileOutputStream object to write the bitmap data to the file.
        val out = FileOutputStream(file)
        // Compress the bitmap data and write it to the FileOutputStream object.
        bmp.compress(Bitmap.CompressFormat.PNG, qualityPercent, out)
        // Close the FileOutputStream object.
        out.close()
        // Use the FileProvider class to get a content URI for the file.
        bmpUri = FileProvider.getUriForFile(this, "${this.packageName}.fileprovider", file)
    } catch (e: IOException) {
        // If an IOException occurs, ignore fallback
    }
    // Return the Uri object, which may be null if an exception was caught.
    return bmpUri
}

/**
 * This function takes a title, content, and contentUri as input parameters
 * and shares the image with the specified title and content on the available
 * sharing platforms.
 */
internal fun Context.shareContent(
    content: String?,
    contentUris: ArrayList<Uri> = arrayListOf(),
) {
    // Create a new Intent object with the ACTION_SEND action.
    val action = if (contentUris.size > 1) Intent.ACTION_SEND_MULTIPLE else Intent.ACTION_SEND
    val intent = Intent(action)
    // Set the MIME type of the Intent to "image/png", "*/*" or text/plain
    intent.type =
        when (contentUris.size) {
            0 -> "text/plain"
            1 -> "image/png"
            else -> "*/*"
        }

    // Add the content as extra data to the Intent.
    content?.let {
        intent.putExtra(Intent.EXTRA_TEXT, content)
    }
    // Add the content URIs as extra data to the Intent.
    if (contentUris.size == 1) {
        intent.putExtra(Intent.EXTRA_STREAM, contentUris.first())
    } else if (contentUris.size > 1) {
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, contentUris)
    }
    // Add a flag to grant read permission to the receiving app for the content URI.
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    // Create a chooser Intent and start the activity.
    val chooserIntent =
        Intent.createChooser(intent, null)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(chooserIntent)
}
