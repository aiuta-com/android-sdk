package sample.tryon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationItem
import com.aiuta.fashionsdk.tryon.core.tryon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val aiuta: Aiuta = MainApplication.aiuta,
) : ViewModel() {
    val aiutaTryOn by lazy { aiuta.tryon }

    val activeProductItems: MutableStateFlow<List<ProductGenerationItem>?> = MutableStateFlow(null)

    fun init() {
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
            activeProductItems.value = productItems?.result
        }
    }
}
