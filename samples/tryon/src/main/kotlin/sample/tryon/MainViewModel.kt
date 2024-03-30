package sample.tryon

import androidx.lifecycle.ViewModel
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.core.tryon

class MainViewModel(
    val aiuta: Aiuta = MainApplication.aiuta,
) : ViewModel() {
    val aiutaTryOn by lazy { aiuta.tryon }
}
