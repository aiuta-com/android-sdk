package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.disclaimer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components.SheetDivider

@Composable
internal fun ColumnScope.FitDisclaimerSheet(text: String) {
    val theme = LocalTheme.current

    SheetDivider(topPadding = 18.dp)

    Spacer(Modifier.height(30.dp))

    Text(
        modifier = Modifier.padding(horizontal = 16.dp),
        text = text,
        style =
            MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 17.sp,
                lineHeight = 22.sp,
            ),
        color = theme.colors.primary,
        textAlign = TextAlign.Start,
    )

    Spacer(Modifier.height(30.dp))
}
