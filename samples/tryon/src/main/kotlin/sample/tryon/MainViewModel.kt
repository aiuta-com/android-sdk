package sample.tryon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.tryon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val aiuta: Aiuta = MainApplication.aiuta,
) : ViewModel() {
    val aiutaTryOn by lazy { aiuta.tryon }

    val activeSKUItems: MutableStateFlow<List<SKUGenerationItem>?> = MutableStateFlow(null)

    fun init() {
        viewModelScope.launch {
            // Let's get catalogs
            val catalogs = aiutaTryOn.getSKUCatalogs().result

            // Take first catalog and get first page of sku items
            val skuItems =
                catalogs.firstOrNull()?.let {
                    aiutaTryOn.getSKUItems(
                        catalogName = it.catalogName,
                    )
                }

            // And finally take first sku item
            activeSKUItems.value = skuItems?.result
        }
    }
}
