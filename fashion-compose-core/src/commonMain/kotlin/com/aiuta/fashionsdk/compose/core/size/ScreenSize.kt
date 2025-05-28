package com.aiuta.fashionsdk.compose.core.size

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp

/**
 * Immutable class representing screen dimensions in density-independent pixels.
 *
 * This class provides a convenient way to access screen size information within
 * Compose UI components, allowing for responsive design implementations.
 *
 * ```kotlin
 * @Composable
 * fun ResponsiveLayout() {
 *     val screenSize = rememberScreenSize()
 *
 *     if (screenSize.widthDp > 600.dp) {
 *         // Tablet layout
 *         TabletLayout()
 *     } else {
 *         // Phone layout
 *         PhoneLayout()
 *     }
 * }
 * ```
 *
 * @property heightDp Screen height in density-independent pixels
 * @property widthDp Screen width in density-independent pixels
 */
@Immutable
public class ScreenSize(
    public val heightDp: Dp,
    public val widthDp: Dp,
)

/**
 * Remembers and provides the current screen size in density-independent pixels.
 *
 * This composable function calculates the screen dimensions based on the current
 * window container size and density. The result is remembered and will be
 * recalculated when the density or window information changes.
 *
 * ```kotlin
 * @Composable
 * fun AdaptiveContent() {
 *     val screenSize = rememberScreenSize()
 *
 *     Column {
 *         Text("Screen: ${screenSize.widthDp} x ${screenSize.heightDp}")
 *
 *         when {
 *             screenSize.widthDp < 480.dp -> CompactLayout()
 *             screenSize.widthDp < 840.dp -> MediumLayout()
 *             else -> ExpandedLayout()
 *         }
 *     }
 * }
 * ```
 *
 * @return [ScreenSize] containing current screen dimensions
 */
@Composable
public fun rememberScreenSize(): ScreenSize {
    val density = LocalDensity.current
    val windowInfo = LocalWindowInfo.current

    return remember(density, windowInfo) {
        ScreenSize(
            heightDp = with(density) { windowInfo.containerSize.height.toDp() },
            widthDp = with(density) { windowInfo.containerSize.width.toDp() },
        )
    }
}
