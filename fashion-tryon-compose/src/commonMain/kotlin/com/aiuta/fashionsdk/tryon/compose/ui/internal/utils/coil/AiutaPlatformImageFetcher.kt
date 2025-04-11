package com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.coil

import coil3.ImageLoader
import coil3.decode.DataSource
import coil3.decode.ImageSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.SourceFetchResult
import coil3.request.Options
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.core.domain.models.file.AiutaPlatformFile
import com.aiuta.fashionsdk.tryon.core.domain.models.file.readByteArray
import okio.Buffer

internal expect fun coil3.PlatformContext.toAiutaPlatformContext(): AiutaPlatformContext

internal class AiutaPlatformImageFetcher(
    private val file: AiutaPlatformFile,
    private val options: Options,
) : Fetcher {

    override suspend fun fetch(): FetchResult = SourceFetchResult(
        source = ImageSource(
            source = Buffer().apply {
                write(file.readByteArray(options.context.toAiutaPlatformContext()))
            },
            fileSystem = options.fileSystem,
        ),
        mimeType = null,
        dataSource = DataSource.MEMORY,
    )

    public class Factory : Fetcher.Factory<AiutaPlatformFile> {
        override fun create(
            data: AiutaPlatformFile,
            options: Options,
            imageLoader: ImageLoader,
        ): Fetcher = AiutaPlatformImageFetcher(data, options)
    }
}
