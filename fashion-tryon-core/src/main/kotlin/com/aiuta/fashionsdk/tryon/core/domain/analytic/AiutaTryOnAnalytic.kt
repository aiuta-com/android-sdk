package com.aiuta.fashionsdk.tryon.core.domain.analytic

import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.model.FinishTryOn
import com.aiuta.fashionsdk.internal.analytic.model.StartTryOn
import com.aiuta.fashionsdk.internal.analytic.model.TryOnError
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer

private const val MILLISECONDS_IN_SECOND = 1000L

internal fun InternalAiutaAnalytic.sendStartTryOnEvent(container: SKUGenerationContainer) {
    sendEvent(StartTryOn) {
        put(
            key = StartTryOn.SKU_ID_PARAM,
            value = container.skuId,
        )
        put(
            key = StartTryOn.SKU_CATALOG_NAME_PARAM,
            value = container.skuCatalogName,
        )
    }
}

internal fun InternalAiutaAnalytic.sendFinishTryOnEvent(
    container: SKUGenerationContainer,
    loadingTimeMillis: Long,
) {
    val loadingTimeSeconds = loadingTimeMillis / MILLISECONDS_IN_SECOND

    sendEvent(FinishTryOn) {
        put(
            key = FinishTryOn.SKU_ID_PARAM,
            value = container.skuId,
        )
        put(
            key = FinishTryOn.SKU_CATALOG_NAME_PARAM,
            value = container.skuCatalogName,
        )
        put(
            key = FinishTryOn.GENERATION_TIME_PARAM,
            value = loadingTimeSeconds.toString(),
        )
    }
}

internal fun InternalAiutaAnalytic.sendTryOnErrorEvent(type: TryOnError.Type) {
    sendEvent(TryOnError) {
        put(
            key = TryOnError.TYPE_PARAM,
            value = type.value,
        )
    }
}
