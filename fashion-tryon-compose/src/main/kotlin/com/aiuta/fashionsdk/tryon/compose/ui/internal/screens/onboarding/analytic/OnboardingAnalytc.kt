package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.onboarding.analytic

import com.aiuta.fashionsdk.internal.analytic.model.ContinueOnBoarding
import com.aiuta.fashionsdk.internal.analytic.model.FinishOnBoarding
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendContinueOnBoardingEvent(newPage: Int) {
    analytic.sendEvent(ContinueOnBoarding) {
        put(
            key = ContinueOnBoarding.PAGE_PARAM,
            value = newPage.toString(),
        )
    }
}

internal fun FashionTryOnController.sendFinishOnBoardingEvent() {
    analytic.sendEvent(FinishOnBoarding)
}
