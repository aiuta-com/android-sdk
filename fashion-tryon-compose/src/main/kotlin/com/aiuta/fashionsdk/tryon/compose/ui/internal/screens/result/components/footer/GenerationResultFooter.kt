package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.connection.rememberGenerationResultConnection
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultController
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BoxWithConstraintsScope.GenerationResultFooter(
    modifier: Modifier = Modifier,
    generationResultController: GenerationResultController,
) {
    val theme = LocalTheme.current

    val connection =
        rememberGenerationResultConnection(
            verticalSwipeState = generationResultController.verticalSwipeStateV2,
        )

    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        0,
                        (generationResultController.verticalSwipeStateV2.offset).roundToInt(),
                    )
                }
                .background(
                    color = Color.Red,
                    shape = theme.shapes.bottomSheet,
                )
                .anchoredDraggable(
                    state = generationResultController.verticalSwipeStateV2,
                    orientation = Orientation.Vertical,
                )
                .nestedScroll(connection),
    ) {
        Box(Modifier.fillMaxSize().background(Color.Red))
    }
}
