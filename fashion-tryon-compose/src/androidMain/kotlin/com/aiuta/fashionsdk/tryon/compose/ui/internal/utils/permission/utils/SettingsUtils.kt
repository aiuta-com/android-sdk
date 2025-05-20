package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.permission.utils

import android.content.Context
import android.content.Intent
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import androidx.core.net.toUri

internal fun Context.openSettings() {
    try {
        Intent(ACTION_APPLICATION_DETAILS_SETTINGS, "package:$packageName".toUri()).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    } catch (e: Exception) {
        // Failed to open settings
    }
}
