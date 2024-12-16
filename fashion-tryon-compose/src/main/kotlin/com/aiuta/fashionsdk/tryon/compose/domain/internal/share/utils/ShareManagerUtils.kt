package com.aiuta.fashionsdk.tryon.compose.domain.internal.share.utils

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalyticFactory
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.internal.analytic.model.ShareEvent
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private const val PAGE_ID_KEY = "pageIdKey"
private const val PRODUCT_ID_KEY = "productIdKey"
private const val SHARE_REQUEST_CODE = 100

/**
 * This function takes a title, content, and contentUri as input parameters
 * and shares the image with the specified title and content on the available
 * sharing platforms.
 */

internal fun Context.shareContent(
    content: String?,
    pageId: AiutaAnalyticPageId,
    productId: String?,
    contentUris: ArrayList<Uri> = arrayListOf(),
) {
    // Create a new Intent object with the ACTION_SEND action.
    val action = if (contentUris.size > 1) Intent.ACTION_SEND_MULTIPLE else Intent.ACTION_SEND
    val intent = Intent(action)
    // Set the MIME type of the Intent to "image/png", "*/*" or text/plain
    intent.type =
        when (contentUris.size) {
            0 -> "text/plain"
            else -> "image/*"
        }

    // Init back receiver
    val pi =
        PendingIntent.getBroadcast(
            this,
            SHARE_REQUEST_CODE,
            Intent(this, ShareBroadcastReceiver::class.java).apply {
                putExtra(PAGE_ID_KEY, Json.encodeToString(pageId))
                putExtra(PRODUCT_ID_KEY, productId)
            },
            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
        )

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
        Intent
            .createChooser(intent, null, pi.intentSender)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(chooserIntent)
}

internal class ShareBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(
        context: Context?,
        intent: Intent?,
    ) {
        val clickedComponent: ComponentName? =
            intent?.getParcelableExtra(
                Intent.EXTRA_CHOSEN_COMPONENT,
            )
        val pageId = intent?.getStringExtra(PAGE_ID_KEY)
        val productId = intent?.getStringExtra(PRODUCT_ID_KEY)

        InternalAiutaAnalyticFactory.getInternalAiutaAnalytic()?.sendEvent(
            event =
                ShareEvent(
                    pageId = pageId?.let { Json.decodeFromString(it) },
                    productId = productId,
                    target = clickedComponent?.packageName,
                ),
        )
    }
}
