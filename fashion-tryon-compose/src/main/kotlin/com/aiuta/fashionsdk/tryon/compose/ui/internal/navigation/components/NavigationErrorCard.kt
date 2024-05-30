package com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.FashionIcon
import com.aiuta.fashionsdk.compose.tokens.utils.clickableUnindicated
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.hideErrorState

internal const val DEFAULT_SHOWING_DELAY = 4000L

@Composable
internal fun NavigationErrorCard(
    modifier: Modifier = Modifier,
    errorState: FashionTryOnErrorState,
) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    Row(
        modifier =
            modifier
                .background(
                    color = theme.colors.error,
                    shape = RoundedCornerShape(16.dp),
                )
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = ImageVector.vectorResource(FashionIcon.Error36),
            contentDescription = null,
            tint = theme.colors.onDark,
        )

        Spacer(Modifier.width(12.dp))

        Text(
            modifier = Modifier.weight(1f),
            text = errorState.message,
            style = MaterialTheme.typography.body1,
            color = theme.colors.onDark,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.width(12.dp))

        Icon(
            modifier =
                Modifier
                    .size(36.dp)
                    .clickableUnindicated {
                        controller.hideErrorState()
                    },
            imageVector = ImageVector.vectorResource(FashionIcon.Cross36),
            contentDescription = null,
            tint = theme.colors.onDark,
        )
    }
}
