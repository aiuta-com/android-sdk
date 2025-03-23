package sample.tryon.kmp

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import com.aiuta.fashionsdk.tryon.core.tryon

// TODO Refactor sample
fun MainViewController() = ComposeUIViewController {
    val aiuta = remember {
        Aiuta.Builder()
            .setAuthenticationStrategy(
                authenticationStrategy =
                    ApiKeyAuthenticationStrategy(
                        apiKey = "TODO",
                    ),
            )
            .setSubscriptionId("TODO")
            .setPlatformContext(AiutaPlatformContext())
            .build()
    }
    val aiutaTryOn = remember { aiuta.tryon }

    LaunchedEffect(Unit) {
        // Let's get catalogs
        val catalogs = aiutaTryOn.getSKUCatalogs().result
        println("MainViewController: catalogs - $catalogs")

        // Take first catalog and get first page of sku items
        val skuItems =
            catalogs.firstOrNull()?.let {
                aiutaTryOn.getSKUItems(
                    catalogName = it.catalogName,
                )
            }?.result?.firstOrNull()
        println("MainViewController: skuItems - $skuItems")

        val imageId = "TODO"
        val imageUrl = "TODO"

        skuItems?.let {
            println("MainViewController: star generation")
            aiutaTryOn.startSKUGeneration(
                container = SKUGenerationUrlContainer(
                    fileId = imageId,
                    fileUrl = imageUrl,
                    skuId = skuItems.skuId,
                    skuCatalogName = skuItems.catalogName,
                ),
            ).collect { result ->
                println("MainViewController: generation result - $result")
            }
        }
    }

    App()
}
