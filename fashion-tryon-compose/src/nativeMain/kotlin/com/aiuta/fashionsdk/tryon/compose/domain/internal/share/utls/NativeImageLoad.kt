package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utls

import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.Foundation.NSURLSession
import platform.Foundation.dataTaskWithURL
import platform.UIKit.UIImage

@OptIn(ExperimentalForeignApi::class)
internal suspend fun nativeLoad(
    urlString: String,
) = suspendCoroutine { continuation ->
    val nsUrl = NSURL.URLWithString(urlString)

    if (nsUrl == null) {
        continuation.resumeWithException(IllegalArgumentException("Not solved url"))
        return@suspendCoroutine
    }

    val session = NSURLSession.sharedSession

    val task = session.dataTaskWithURL(nsUrl) { data, nsUrlResponse, error ->
        if (data != null && error == null) {
            val image = UIImage(data)
            continuation.resume(image)
        } else {
            continuation.resumeWithException(IllegalArgumentException("Error downloading image: ${error?.localizedDescription}"))
        }
    }

    task.resume()
}
