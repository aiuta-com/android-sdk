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
import com.aiuta.fashionsdk.logger.DebugAiutaLogger
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.rememberAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationItem
import com.aiuta.fashionsdk.tryon.core.tryon

// TODO Refactor sample
fun MainViewController() = ComposeUIViewController {
    val aiuta = remember {
        aiuta {
            authenticationStrategy = ApiKeyAuthenticationStrategy("TODO")
            platformContext = AiutaPlatformContext.INSTANCE
            logger = DebugAiutaLogger()
        }
    }
    val aiutaTryOn = remember { aiuta.tryon }
    val activeSKUItems = remember { mutableStateOf<List<ProductGenerationItem>?>(null) }

    LaunchedEffect(Unit) {
        // Let's get catalogs
        val catalogs = aiutaTryOn.getProductCatalogs().result

        // Take first catalog and get first page of sku items
        activeSKUItems.value = catalogs.firstOrNull()?.let {
            aiutaTryOn.getProductItems(
                catalogName = it.catalogName,
            )
        }?.result
    }


    activeSKUItems.value?.firstOrNull()?.let { firstActiveSKUItem ->
        val mockProductItem =
            remember {
                ProductItem(
                    id = firstActiveSKUItem.productId,
                    catalogName = firstActiveSKUItem.catalogName,
                    description = "MOCK 90s straight leg jeans in light blue",
                    imageUrls = firstActiveSKUItem.imageUrls,
                    localizedPrice = "$34.99",
                    localizedOldPrice = "$41.99",
                    store = "MOCK STORE",
                )
            }

        val aiutaConfiguration = rememberAiutaConfiguration {
            userInterface = defaultAiutaUserInterfaceConfiguration(
                actions = object : AiutaUserInterfaceActions {
                    override val closeClick: () -> Unit = {
                        println("Rise Close")
                    }
                },
            )

            tryOnConfiguration = defaultAiutaTryOnConfiguration(aiuta)
        }

        AiutaTryOnFlow(
            modifier = Modifier.fillMaxSize(),
            aiutaConfiguration = aiutaConfiguration,
            productForGeneration = mockProductItem,
        )
    }
}
