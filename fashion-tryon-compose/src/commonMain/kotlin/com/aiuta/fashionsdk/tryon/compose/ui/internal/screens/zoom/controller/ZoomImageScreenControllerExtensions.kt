package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.zoom.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.zoom.ZoomImageUiModel
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.zoomable.toggleScale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Zoom
internal fun ZoomImageController.enableZoomState() = changeZoomState(ZoomImageState.ENABLE)

internal fun ZoomImageController.disableZoomState() = changeZoomState(ZoomImageState.DISABLE)

internal fun ZoomImageController.isZoomEnable() = zoomState.value == ZoomImageState.ENABLE

internal fun ZoomImageController.changeZoomState(newZoomState: ZoomImageState) {
    zoomState.value = newZoomState
}

// Transition
internal fun ZoomImageController.activateTransition() = changeTransitionState(
    TransitionState.ACTIVE,
)

internal fun ZoomImageController.deactivateTransition() = changeTransitionState(
    TransitionState.IDLE,
)

internal fun ZoomImageController.isTransitionActive() = transitionState.value == TransitionState.ACTIVE

@Composable
internal fun ZoomImageController.isTransitionActiveListener() = remember(transitionState.value) {
    derivedStateOf {
        isTransitionActive()
    }
}

internal fun ZoomImageController.changeTransitionState(newTransitionState: TransitionState) {
    transitionState.value = newTransitionState
}

// Shared image data
internal fun ZoomImageController.setActiveSharedImageModel(newModel: ZoomImageUiModel) {
    sharedImage.value = newModel
}

internal fun ZoomImageController.openZoomImageScreen(model: ZoomImageUiModel) {
    setActiveSharedImageModel(model)
    activateTransition()
    enableZoomState()
}

internal fun ZoomImageController.closeZoomImageScreen(scope: CoroutineScope) {
    scope.launch {
        imageZoomState.toggleScale(
            targetScale = 1f,
            position =
            Offset(
                x = maxContentWidth / 2f,
                y = maxContentHeight / 2f,
            ),
        )
    }
    deactivateTransition()
}
