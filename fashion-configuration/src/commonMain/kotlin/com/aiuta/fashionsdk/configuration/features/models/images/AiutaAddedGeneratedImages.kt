package com.aiuta.fashionsdk.configuration.features.models.images

public interface AiutaAddedGeneratedImages {

    public class SingleTryOn(
        public val productId: String,
        public val generations: List<AiutaHistoryImage>,
    ) : AiutaAddedGeneratedImages

    public class MultiTryOn(
        public val productIds: List<String>,
        public val generations: List<AiutaHistoryImage>,
    ) : AiutaAddedGeneratedImages
}
