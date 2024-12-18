package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import android.content.Context
import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow

internal interface GeneratedImageInteractor {
    suspend fun insertAll(
        generatedSkuId: String,
        images: List<GeneratedImageUIModel>,
    )

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>>

    suspend fun remove(generatedImages: List<GeneratedImageUIModel>)

    suspend fun removeAll()

    fun countFlow(): Flow<Int>

    companion object {
        fun getInstance(context: Context): GeneratedImageInteractor {
            return LocalGeneratedImageInteractor.getInstance(context)
        }

        fun getInstance(dataProvider: AiutaDataProvider): GeneratedImageInteractor {
            return HostGeneratedImageInteractor.getInstance(dataProvider)
        }
    }
}
