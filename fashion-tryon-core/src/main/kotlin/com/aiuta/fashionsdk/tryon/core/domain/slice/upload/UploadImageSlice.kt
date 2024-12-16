package com.aiuta.fashionsdk.tryon.core.domain.slice.upload

import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.network.exceptions.FashionNetworkIsDisconnected
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUriContainer
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException
import java.io.FileNotFoundException

/**
 * Slice responsible for uploading images
 */
internal interface UploadImageSlice {
    /**
     * Will try to compress image by default config (or use
     * default, if not), and then will upload image on backend
     *
     * @param fileUri is uri of uploaded file
     * @param fileName is name of file with input image
     *
     * @throws FashionReadBytesException if the provided ByteArray will be empty or null
     * @throws FileNotFoundException if the provided URI could not be opened.
     * @throws FashionIOException
     * @throws FashionNetworkIsDisconnected
     */
    suspend fun uploadImage(
        container: SKUGenerationUriContainer,
        fileName: String,
    ): UploadedImage
}
