package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import android.content.Context
import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import kotlinx.coroutines.flow.Flow

internal interface GeneratedImageInteractor {
    suspend fun insertAll(imageUrls: List<String>)

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImage>>

    suspend fun remove(generatedImages: List<GeneratedImage>)

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
