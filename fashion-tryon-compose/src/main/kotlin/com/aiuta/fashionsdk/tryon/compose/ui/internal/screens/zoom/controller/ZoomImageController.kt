package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.Constraints
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.zoomable.ZoomState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.zoomable.rememberZoomState

internal enum class ZoomImageState {
    ENABLE,

    DISABLE,
}

internal enum class TransitionState {
    IDLE,

    ACTIVE,
}

@Composable
internal fun rememberZoomImageController(
    constraints: Constraints,
    imageZoomState: ZoomState = rememberZoomState(),
) = remember(constraints) {
    ZoomImageController(
        constraints = constraints,
        imageZoomState = imageZoomState,
    )
}

@Immutable
internal class ZoomImageController(
    constraints: Constraints,
    public val imageZoomState: ZoomState,
) {
    public var maxContentWidth = constraints.maxWidth
    public var maxContentHeight = constraints.maxHeight
    public var maxSize =
        Size(
            width = maxContentWidth.toFloat(),
            height = maxContentHeight.toFloat(),
        )

    public val zoomState: MutableState<ZoomImageState> = mutableStateOf(ZoomImageState.DISABLE)
    public val transitionState: MutableState<TransitionState> = mutableStateOf(TransitionState.IDLE)

    public val sharedImage: MutableState<ZoomImageUiModel> = mutableStateOf(ZoomImageUiModel.EMPTY)
}
