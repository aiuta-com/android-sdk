package sample.tryon.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.rememberAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.AiutaTryOnFlow
import sample.tryon.MainViewModel

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current

    val activeProductItems = viewModel.activeProductItems.collectAsStateWithLifecycle()
    val firstActiveSKUItem =
        remember(activeProductItems.value) {
            activeProductItems.value?.firstOrNull()
        }

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    firstActiveSKUItem?.let {
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
                        context.makeToast("Rise Close")
                    }
                },
            )

            tryOnConfiguration = defaultAiutaTryOnConfiguration(viewModel.aiuta)
        }

        AiutaTryOnFlow(
            modifier = Modifier.fillMaxSize(),
            aiutaConfiguration = aiutaConfiguration,
            productForGeneration = mockProductItem,
        )
    }
}
