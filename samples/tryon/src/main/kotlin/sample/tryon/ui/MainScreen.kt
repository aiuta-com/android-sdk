package sample.tryon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.defaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import sample.tryon.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current

    val activeSKUItems = viewModel.activeSKUItems.collectAsStateWithLifecycle()
    val firstActiveSKUItem =
        remember(activeSKUItems.value) {
            activeSKUItems.value?.firstOrNull()
        }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    firstActiveSKUItem?.let {
        val mockSKUItem =
            remember {
                SKUItem(
                    skuId = firstActiveSKUItem.skuId,
                    description = "MOCK 90s straight leg jeans in light blue",
                    imageUrls = firstActiveSKUItem.imageUrls,
                    price = 41.99F,
                    priceDiscounted = 34.99F,
                    priceCurrency = "\$",
                    store = "MOCK STORE",
                    generateMoreSKU =
                        activeSKUItems.value?.map { skuItem ->
                            SKUItem(
                                skuId = skuItem.skuId,
                                catalogName = skuItem.catalogName,
                                description = "MOCK 90s straight leg jeans in light blue",
                                imageUrls = skuItem.imageUrls,
                                price = 41.99F,
                                priceDiscounted = 34.99F,
                                priceCurrency = "\$",
                                store = "MOCK STORE",
                            )
                        } ?: emptyList(),
                    additionalShareInfo =
                        """
                        You can find more information about this item here:
                        https://some-cool-website.com/product
                        """.trimIndent(),
                )
            }

        val mockAiutaTryOnListeners =
            remember {
                AiutaTryOnListeners(
                    addToWishlistClick = {
                        context.makeToast("Rise Add to Wishlist")
                    },
                    addToCartClick = {
                        context.makeToast("Rise Add to cart")
                    },
                    moreDetailsClick = {
                        context.makeToast("Rise More details")
                    },
                    closeClick = {
                        context.makeToast("Rise Close")
                    },
                )
            }

        val mockAiutaConfiguration =
            remember {
                defaultAiutaTryOnConfiguration(
                    photoSelectionLimit = 5,
                )
            }

        AiutaTryOnFlow(
            modifier = Modifier.fillMaxSize(),
            aiuta = { viewModel.aiuta },
            aiutaTryOn = { viewModel.aiutaTryOn },
            aiutaTryOnListeners = { mockAiutaTryOnListeners },
            aiutaTryOnConfiguration = { mockAiutaConfiguration },
            skuForGeneration = { mockSKUItem },
        )
    }
}
