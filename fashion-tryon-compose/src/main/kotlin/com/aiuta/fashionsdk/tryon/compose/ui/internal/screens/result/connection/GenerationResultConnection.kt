package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.connection

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerateResultStatus

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun rememberGenerationResultConnection(
    verticalSwipeState: AnchoredDraggableState<GenerateResultStatus>,
): GenerationResultConnection {
    return remember {
        GenerationResultConnection(
            verticalSwipeState = verticalSwipeState,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
internal class GenerationResultConnection(
    val verticalSwipeState: AnchoredDraggableState<GenerateResultStatus>,
) : NestedScrollConnection {
    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource,
    ): Offset {
        val delta = available.y
        return if (delta < 0) {
            verticalSwipeState.dispatchRawDelta(delta).toOffset()
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource,
    ): Offset {
        val delta = available.y
        return verticalSwipeState.dispatchRawDelta(delta).toOffset()
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return if (available.y < 0) {
            verticalSwipeState.settle(available.y)
            available
        } else {
            Velocity.Zero
        }
    }

    override suspend fun onPostFling(
        consumed: Velocity,
        available: Velocity,
    ): Velocity {
        verticalSwipeState.settle(velocity = available.y)
        return super.onPostFling(consumed, available)
    }

    private fun Float.toOffset() = Offset(0f, this)
}
