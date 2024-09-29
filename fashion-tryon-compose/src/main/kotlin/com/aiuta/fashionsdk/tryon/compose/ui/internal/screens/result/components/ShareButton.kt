package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.internal.analytic.model.ShareGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.internal.share.ShareManager
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendShareGeneratedImageEvent
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun ShareButton(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val context = LocalContext.current
    val controller = LocalController.current
    val theme = LocalTheme.current
    val shareManager =
        remember {
            ShareManager(context)
        }

    Box(
        modifier =
            modifier
                .background(
                    shape = CircleShape,
                    color = Color.White,
                )
                .clickableUnindicated {
                    val imageUrls = listOfNotNull(imageUrl)
                    controller.sendShareGeneratedImageEvent(
                        origin = ShareGeneratedImage.Origin.RESULT_SCREEN,
                        count = imageUrls.size,
                        additionalShareInfo = controller.activeSKUItem.value.additionalShareInfo,
                    )
                    shareManager.share(
                        content = controller.activeSKUItem.value.additionalShareInfo,
                        imageUrls = imageUrls,
                        watermarkRes = theme.watermarkRes,
                        origin = ShareGeneratedImage.Origin.RESULT_SCREEN,
                    )
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(FashionIcon.Share24),
            contentDescription = null,
            tint = Color.Black,
        )
    }
}
