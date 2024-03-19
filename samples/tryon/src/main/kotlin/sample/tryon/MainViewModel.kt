package sample.tryon

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.tryon
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    val aiuta: Aiuta = MainApplication.aiuta,
) : ViewModel() {
    val aiutaTryOn by lazy { aiuta.tryon }

    val skuGenerationStatus: StateFlow<SKUGenerationStatus> = aiutaTryOn.skuGenerationStatus

    fun startSKUGeneration(fileUris: List<Uri>) {
        viewModelScope.launch {
            val skuItems = aiutaTryOn.getSKUItems()

            Log.d(TAG, "startSKUGeneration(): fileUris - $fileUris, skuItems - $skuItems")
            skuItems.result.firstOrNull()?.let { skuItem ->
                aiutaTryOn.startSKUGeneration(
                    containers =
                        fileUris.map { uri ->
                            SKUGenerationContainer(
                                fileUri = uri,
                                skuId = skuItem.skuId,
                            )
                        },
                )
            }
        }
    }

    private companion object {
        const val TAG = "TAG_MainViewModel"
    }
}
