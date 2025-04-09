package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.controller.state

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface OnboardingState {
    val pageTitle: String?

    fun pageSize(): Int = 1
}
