package sample.tryon.kmp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiuta.fashionsdk.tryon.compose.configuration.listeners.AiutaTryOnListeners
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.defaults.rememberDefaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.defaults.rememberDefaultAiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import sample.tryon.kmp.MainViewModel

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
                )
            }

        val mockAiutaTryOnListeners =
            remember {
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
                )
            }

        val mockAiutaConfiguration = rememberDefaultAiutaTryOnConfiguration(
            aiuta = viewModel.aiuta,
        )

        val mockAiutaUIConfiguration = rememberDefaultAiutaUserInterfaceConfiguration(
            actions = object : AiutaUserInterfaceActions {
                override val closeClick: () -> Unit = {
                    context.makeToast("Rise Close")
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
