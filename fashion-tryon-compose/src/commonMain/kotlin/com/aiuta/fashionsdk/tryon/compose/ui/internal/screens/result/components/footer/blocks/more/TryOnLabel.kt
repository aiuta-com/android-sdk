package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.images.AiutaIcon
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

@Composable
internal fun TryOnLabel(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    val imageSelectorFeature = strictProvideFeature<AiutaImageSelectorFeature>()

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
        AiutaIcon(
            modifier = Modifier.size(20.dp),
            icon = theme.icons.magic20,
            contentDescription = null,
            tint = theme.colors.onDark,
        )

        Spacer(Modifier.width(7.dp))

        Text(
            text = imageSelectorFeature.strings.imageSelectorButtonTryOn,
            style = theme.typography.smallButton,
            color = theme.colors.onDark,
            textAlign = TextAlign.Start,
        )
    }
}
