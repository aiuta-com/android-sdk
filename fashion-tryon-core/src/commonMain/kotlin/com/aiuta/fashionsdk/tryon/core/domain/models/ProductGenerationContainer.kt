package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.io.AiutaPlatformFile
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn

/**
 * Base container for starting product generation flow
 *
 * @see AiutaTryOn.startProductGeneration
 */
public sealed interface ProductGenerationContainer {
    public val productId: String
    public val productCatalogName: String?
}

/**
 * Container for starting product generation flow with file uri,
 * provided from system
 *
 * @see AiutaTryOn.startProductGeneration
 * @see ProductGenerationContainer
 */
public class ProductGenerationPlatformImageContainer(
    public val platformFile: AiutaPlatformFile,
    override val productId: String,
    override val productCatalogName: String? = null,
) : ProductGenerationContainer

/**
 * Container for starting product generation flow with already
 * uploaded image on Aiuta backend
 *
 * @see AiutaTryOn.startProductGeneration
 * @see ProductGenerationContainer
 */
public class ProductGenerationUrlContainer(
    public val fileId: String,
    public val fileUrl: String,
    override val productId: String,
    override val productCatalogName: String? = null,
) : ProductGenerationContainer
