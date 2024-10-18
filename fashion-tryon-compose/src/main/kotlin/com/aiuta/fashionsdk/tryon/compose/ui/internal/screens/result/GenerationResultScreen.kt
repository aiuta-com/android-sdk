package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendPageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.appbar.MainAppBar
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.body.GenerationResultBody
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.common.ThanksFeedbackBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.DisclaimerBlock
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.GenerationResultFooter
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.GenerationResultListener
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.appbarHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.disclaimerHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.disclaimerOffset
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.imagesHeight
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller.rememberGenerationResultController

@Composable
internal fun GenerationResultScreen(modifier: Modifier = Modifier) {
    sendPageEvent(pageId = AiutaAnalyticPageId.RESULTS)

    GenerationResultListener()

    GenerationResultScreenContent(modifier = Modifier.fillMaxSize())
}

@Composable
private fun GenerationResultScreenContent(modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = modifier,
    ) {
        val generationResultController = rememberGenerationResultController(maxHeight = maxHeight)

        MainAppBar(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .height(appbarHeight(maxHeight))
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
        )

        GenerationResultBody(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = appbarHeight(maxHeight))
                    .height(imagesHeight(maxHeight))
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
            generationResultController = generationResultController,
        )

        DisclaimerBlock(
            modifier =
                Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = disclaimerOffset(maxHeight))
                    .height(disclaimerHeight())
                    .fillMaxWidth(),
        )

        GenerationResultFooter(
            generationResultController = generationResultController,
        )

        ThanksFeedbackBlock(
            modifier = Modifier.align(Alignment.Center),
            generationResultController = generationResultController,
        )
    }
}
