package sample.tryon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiuta.fashionsdk.compose.tokens.rememberAiutaTheme
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.language.EnglishLanguage
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import com.aiuta.fashionsdk.tryon.icons.rememberDefaultAiutaIcons
import com.aiuta.fashionsdk.tryon.images.rememberDefaultAiutaImages
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
                    catalogName = firstActiveSKUItem.catalogName,
                    description = "MOCK 90s straight leg jeans in light blue",
                    imageUrls = firstActiveSKUItem.imageUrls,
                    localizedPrice = "$34.99",
                    localizedOldPrice = "$41.99",
                    store = "MOCK STORE",
                    generateMoreSKU =
                        activeSKUItems.value?.mapIndexed { index, skuItem ->
                            SKUItem(
                                skuId = skuItem.skuId,
                                catalogName = skuItem.catalogName,
                                description = "MOCK 90s straight leg jeans in light blue",
                                imageUrls = skuItem.imageUrls,
                                localizedPrice = "$34.99",
                                localizedOldPrice = "$41.99",
                                store = "MOCK STORE",
                                inWishlist = index % 2 == 0,
                            )
                        } ?: emptyList(),
                    inWishlist = false,
                    additionalShareInfo =
                        """
                        You can find more information about this item here:
                        https://some-cool-website.com/product
                        """.trimIndent(),
                )
            }

        val mockAiutaConfiguration =
            remember {
                AiutaTryOnConfiguration.Builder()
                    .setAiuta(viewModel.aiuta)
                    .setSKUForGeneration(mockSKUItem)
                    .setLanguage(
                        language =
                            EnglishLanguage(
                                brand = "YOUR brand",
                                termsOfServiceUrl = "https://brand.com/tos",
                                privacyPolicyUrl = "https://brand.com/pp",
                                onboardingPageConsentSupplementaryPoints = emptyList(),
                            ),
                    )
                    .setListeners(
                        listeners =
                            AiutaTryOnListeners(
                                addToWishlistClick = {
                                    context.makeToast("Rise Add to wishlist")
                                },
                                addToCartClick = {
                                    context.makeToast("Rise Add to cart")
                                },
                                closeClick = {
                                    context.makeToast("Rise Close")
                                },
                            ),
                    )
                    .build()
            }

        val mockAiutaTheme =
            rememberAiutaTheme(
                icons = rememberDefaultAiutaIcons(),
                images = rememberDefaultAiutaImages(),
            )

        AiutaTryOnFlow(
            modifier = Modifier.fillMaxSize(),
            aiutaTryOnConfiguration = mockAiutaConfiguration,
            aiutaTheme = mockAiutaTheme,
        )
    }
}
