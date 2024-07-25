package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun ColumnScope.SheetDivider(topPadding: Dp = 8.dp) {
    Spacer(Modifier.height(topPadding))

    Divider(
        modifier =
            Modifier
                .width(36.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(6.dp))
                .align(Alignment.CenterHorizontally),
        color = Color(0xFFC5C5C5),
    )
}
