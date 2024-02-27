package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.transition

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith

private const val ANIMATION_DURATION = 400

internal val rightToLeftTransition: ContentTransform =
    slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { fullWidth -> fullWidth },
    ) togetherWith
        slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { fullWidth -> -fullWidth },
        )

internal val leftToRightTransition =
    slideInHorizontally(
        animationSpec = tween(ANIMATION_DURATION),
        initialOffsetX = { fullWidth -> -fullWidth },
    ) togetherWith
        slideOutHorizontally(
            animationSpec = tween(ANIMATION_DURATION),
            targetOffsetX = { fullWidth -> fullWidth },
        )
