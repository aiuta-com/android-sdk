package sample.tryon.kmp.utils

import androidx.compose.runtime.Composable
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationItem

@Composable
fun buildMockProductItem(generationItem: ProductGenerationItem): ProductItem = ProductItem(
    id = generationItem.productId,
    catalogName = generationItem.catalogName,
    description = "MOCK 90s straight leg jeans in light blue",
    imageUrls = generationItem.imageUrls,
    localizedPrice = "$34.99",
    localizedOldPrice = "$41.99",
    store = "MOCK STORE",
)
