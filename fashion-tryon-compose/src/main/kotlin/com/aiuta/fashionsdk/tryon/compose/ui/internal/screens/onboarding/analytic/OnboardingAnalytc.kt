package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic

import com.aiuta.fashionsdk.internal.analytic.model.ContinueOnBoarding
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendContinueOnBoardingEvent(newPage: Int) {
    analytic.sendEvent(
        event =
            ContinueOnBoarding(
                page = newPage.toString(),
            ),
    )
}
