package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalTheme
import kotlin.math.roundToInt

@Composable
internal fun SKUInfo(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val theme = LocalTheme.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = skuItem.store,
            style = MaterialTheme.typography.body1,
            color = theme.colors.secondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(1.dp))

        Text(
            text = skuItem.description,
            style = MaterialTheme.typography.body2,
            color = theme.colors.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(Modifier.height(1.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            skuItem.priceWithCurrency?.let { priceWithCurrency ->
                Text(
                    text = priceWithCurrency,
                    style =
                        MaterialTheme.typography.body1.copy(
                            textDecoration = solveGeneralPriceDecoration(skuItem),
                        ),
                    color = solveGeneralPriceColor(skuItem),
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            skuItem.priceDiscountedWithCurrency?.let { priceDiscountedWithCurrency ->

                Spacer(Modifier.width(4.dp))

                Text(
                    text = priceDiscountedWithCurrency,
                    style = MaterialTheme.typography.body1,
                    color = theme.colors.accent,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                if (skuItem.price != null && skuItem.priceDiscounted != null) {
                    Spacer(Modifier.width(8.dp))

                    DiscountBlock(
                        price = skuItem.price,
                        priceWithDiscount = skuItem.priceDiscounted,
                    )
                }
            }
        }
    }
}

@Composable
internal fun DiscountBlock(
    modifier: Modifier = Modifier,
    price: Float,
    priceWithDiscount: Float,
) {
    val theme = LocalTheme.current
    val discount = (priceWithDiscount * 100 / price - 100).roundToInt()

    Box(
        modifier =
            modifier
                .background(
                    shape = RoundedCornerShape(4.dp),
                    color = theme.colors.accent,
                )
                .padding(
                    vertical = 2.dp,
                    horizontal = 4.dp,
                ),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "$discount%",
            style = MaterialTheme.typography.body2,
            color = theme.colors.onDark,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
