package sample.tryon.kmp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.configuration.aiutaConfiguration
import com.aiuta.fashionsdk.configuration.ui.actions.AiutaUserInterfaceActions
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.logger.DebugAiutaLogger
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.defaults.defaultAiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AiutaViewModel : ViewModel() {
    val activeProductItem: MutableStateFlow<ProductGenerationItem?> = MutableStateFlow(null)

    fun buildAiutaConfiguration(context: AiutaPlatformContext) = aiutaConfiguration {
        userInterface = defaultAiutaUserInterfaceConfiguration(
            actions = object : AiutaUserInterfaceActions {
                override val closeClick: () -> Unit = {
                    println("CLICK CLOSE")
                }
            },
        )
        tryOnConfiguration = defaultAiutaTryOnConfiguration(
            aiuta = buildAiuta(context),
        )
    }

    fun loadActiveProduct(aiutaTryOn: AiutaTryOn) {
        viewModelScope.launch {
            // Let's get catalogs
            val catalogs = aiutaTryOn.getProductCatalogs().result

            // Take first catalog and get first page of product items
            val productItems =
                catalogs.firstOrNull()?.let {
                    aiutaTryOn.getProductItems(
                        catalogName = it.catalogName,
                    )
                }

            // And finally take first product item
            activeProductItem.value = productItems?.result?.firstOrNull()
        }
    }

    private fun buildAiuta(context: AiutaPlatformContext): Aiuta = aiuta {
        authenticationStrategy = ApiKeyAuthenticationStrategy(BuildKonfig.AIUTA_API_KEY)
        platformContext = context
        logger = DebugAiutaLogger()
    }
}
