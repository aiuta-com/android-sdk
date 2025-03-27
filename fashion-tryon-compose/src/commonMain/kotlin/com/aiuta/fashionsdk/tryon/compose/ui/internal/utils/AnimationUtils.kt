package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

private const val DURATION_ANIMATION = 500

internal val enterTransitionAnimation =
    slideInVertically(
        animationSpec = tween(durationMillis = DURATION_ANIMATION),
    ) { height -> height } +
        fadeIn(
            animationSpec = tween(durationMillis = DURATION_ANIMATION),
        )
internal val exitTransitionAnimation =
    slideOutVertically(
        animationSpec = tween(durationMillis = DURATION_ANIMATION),
    ) { height -> -height } +
        fadeOut(
            animationSpec = tween(durationMillis = DURATION_ANIMATION),
        )

internal val transitionAnimation = enterTransitionAnimation togetherWith exitTransitionAnimation
