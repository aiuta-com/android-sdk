package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker.exceptions

internal class NotSupportedImageSourceException :
    IllegalStateException(
        "Not supported picker source. Please, set correct feature of image source or support new",
    )
