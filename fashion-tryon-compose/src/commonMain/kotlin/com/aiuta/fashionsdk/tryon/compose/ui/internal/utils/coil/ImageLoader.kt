package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.coil

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import okio.FileSystem

internal fun newImageLoader(
    context: PlatformContext,
    debug: Boolean = false,
): ImageLoader = ImageLoader.Builder(context)
    .memoryCache {
        MemoryCache.Builder()
            // Set the max size to 25% of the app's available memory.
            .maxSizePercent(context, percent = 0.25)
            .build()
    }
    .diskCache {
        DiskCache.Builder()
            .directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
            .maxSizeBytes(512L * 1024 * 1024) // 512MB
            .build()
    }
    // Show a short crossfade when loading images asynchronously.
    .crossfade(true)
    .components { add(AiutaPlatformImageFetcher.Factory()) }
    .apply {
        if (debug) {
            logger(DebugLogger())
        }
    }
    .build()
