package sample.tryon.kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.ComposeUIViewController
import com.aiuta.fashionsdk.aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.defaults.rememberDefaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.defaults.rememberDefaultAiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.tryon

// TODO Refactor sample
fun MainViewController() = ComposeUIViewController {
    val aiuta = remember {
        aiuta {
            authenticationStrategy = ApiKeyAuthenticationStrategy("TODO")
            platformContext = AiutaPlatformContext()
        }
    }
    val aiutaTryOn = remember { aiuta.tryon }
    val activeSKUItems = remember { mutableStateOf<List<SKUGenerationItem>?>(null) }

    LaunchedEffect(Unit) {
        // Let's get catalogs
        val catalogs = aiutaTryOn.getSKUCatalogs().result
        println("MainViewController: catalogs - $catalogs")

        // Take first catalog and get first page of sku items
        activeSKUItems.value = catalogs.firstOrNull()?.let {
            aiutaTryOn.getSKUItems(
                catalogName = it.catalogName,
            )
        }?.result
        println("MainViewController: skuItems - $activeSKUItems")
    }


    activeSKUItems.value?.firstOrNull()?.let { firstActiveSKUItem ->
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
                )
            }

        val mockAiutaTryOnListeners =
            remember {
                AiutaTryOnListeners(
                    addToWishlistClick = {
                        println("Rise Add to wishlist")
                    },
                    addToCartClick = {
                        println("Rise Add to cart")
                    },
                    closeClick = {
                        println("Rise Close")
                    },
                )
            }

        val mockAiutaConfiguration = rememberDefaultAiutaTryOnConfiguration(
            aiuta = aiuta,
        )

        val mockAiutaUIConfiguration = rememberDefaultAiutaUserInterfaceConfiguration(
            actions = object : AiutaUserInterfaceActions {
                override val closeClick: () -> Unit = {
                    println("Rise Close")
                }
            },
        )

        AiutaTryOnFlow(
            modifier = Modifier.fillMaxSize(),
            aiutaTryOnConfiguration = mockAiutaConfiguration,
            aiutaTryOnListeners = mockAiutaTryOnListeners,
            aiutaUserInterfaceConfiguration = mockAiutaUIConfiguration,
            skuForGeneration = mockSKUItem,
        )
    }
}
