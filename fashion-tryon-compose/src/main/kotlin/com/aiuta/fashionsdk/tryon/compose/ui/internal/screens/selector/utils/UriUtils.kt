package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

internal fun Context.openUri(uri: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(uri)))
    } catch (e: Exception) {
        // Failed to open uri, let's ignore
    }
}
