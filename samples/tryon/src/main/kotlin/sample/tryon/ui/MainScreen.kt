package sample.tryon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import sample.tryon.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current

    val mockSKUItem =
        remember {
            SKUItem(
                skuId = "AIUTA-DEMO-0",
                description = "90s straight leg jeans in light blue",
                imageUrls =
                    listOf(
                        "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_0.jpg",
                        "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_1.jpg",
                        "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_2.jpg",
                        "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_3.jpg",
                    ),
                price = 41.99F,
                priceDiscounted = 34.99F,
                priceCurrency = "\$",
                store = "ASOS DESIGN",
                generateMoreSKU =
                    listOf(
                        SKUItem(
                            skuId = "AIUTA-DEMO-0",
                            description = "90s straight leg jeans in light blue",
                            imageUrls =
                                listOf(
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_0.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_1.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_2.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_3.jpg",
                                ),
                            price = 41.99F,
                            priceDiscounted = 34.99F,
                            priceCurrency = "\$",
                            store = "ASOS DESIGN 0",
                        ),
                        SKUItem(
                            skuId = "AIUTA-DEMO-1",
                            description = "90s straight leg jeans in light blue",
                            imageUrls =
                                listOf(
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_1.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_0.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_2.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_3.jpg",
                                ),
                            price = 41.99F,
                            priceDiscounted = 34.99F,
                            priceCurrency = "\$",
                            store = "ASOS DESIGN 1",
                        ),
                        SKUItem(
                            skuId = "AIUTA-DEMO-2",
                            description = "90s straight leg jeans in light blue",
                            imageUrls =
                                listOf(
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_2.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_1.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_0.jpg",
                                    "https://storage.googleapis.com/aiuta_prod_external_api_images/external_api/lora/0001_shein_ohwx_color_and_title_filter/19c1437a4f46b0c6180dbd62a838caedbbf631c3efec36e2e566ded5be452ab0_Multicolor_3.jpg",
                                ),
                            price = 41.99F,
                            priceCurrency = "\$",
                            store = "ASOS DESIGN 2",
                        ),
                    ),
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
                closeClick = {
                    context.makeToast("Rise Close")
                },
            )
        }

    AiutaTryOnFlow(
        modifier = Modifier.fillMaxSize(),
        aiutaTryOnListeners = { mockAiutaTryOnListeners },
        aiutaTryOn = { viewModel.fashionTryOn },
        skuForGeneration = { mockSKUItem },
    )
}
