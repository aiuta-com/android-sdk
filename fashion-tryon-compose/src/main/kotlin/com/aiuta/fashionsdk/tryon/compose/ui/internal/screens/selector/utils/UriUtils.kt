package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri

internal fun Context.openUri(uri: String) {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, uri.toUri()))
    } catch (e: Exception) {
        // Failed to open uri, let's ignore
    }
}
