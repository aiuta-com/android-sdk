package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.compose.tokens.icon.magic16
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnStringResources

@Composable
internal fun TryOnLabel(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current
    val stringResources = LocalAiutaTryOnStringResources.current

    Row(
        modifier =
            modifier
                .background(
                    color = theme.colors.brand,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(
                    horizontal = 10.dp,
                    vertical = 8.dp,
                ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = rememberAsyncImagePainter(theme.icons.magic16),
            contentDescription = null,
            tint = theme.colors.onDark,
        )

        Spacer(Modifier.width(7.dp))

        Text(
            text = stringResources.tryOn,
            style = theme.typography.smallButton,
            color = theme.colors.onDark,
            textAlign = TextAlign.Start,
        )
    }
}
