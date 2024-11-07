package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun rememberGenerationResultController(): GenerationResultController {
    val density = LocalDensity.current
    val controller = LocalController.current
    val configuration = LocalConfiguration.current

    val pagerState =
        rememberPagerState(pageCount = {
            controller.sessionGenerationInteractor.sessionGenerations.size
        })
    val footerListState = rememberLazyGridState()
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(
            bottomSheetState =
                BottomSheetState(
                    initialValue = BottomSheetValue.Collapsed,
                    density = density,
                ),
        )

    return remember(density, configuration) {
        GenerationResultController(
            generationPagerState = pagerState,
            footerListState = footerListState,
            bottomSheetScaffoldState = bottomSheetScaffoldState,
        )
    }.also {
        GenerationResultControllerListener(it)
    }
}

@Immutable
internal class GenerationResultController(
    // Generation pager state
    public val generationPagerState: PagerState,
    // Swipe state
    public val footerListState: LazyGridState,
    public val bottomSheetScaffoldState: BottomSheetScaffoldState,
) {
    public val isThanksFeedbackBlockVisible = mutableStateOf(false)
}
