package com.aiuta.fashionsdk.tryon.core.domain.models.meta

import kotlin.time.Duration
import kotlin.time.TimeSource
import kotlin.time.TimeSource.Monotonic.ValueTimeMark

public class AiutaTryOnMetadata private constructor(
    public val startSecondsTimestamp: ValueTimeMark,
    public val uploadDurationSeconds: Duration,
    public val tryOnDurationSeconds: Duration,
) {
    public class Builder {
        private val startSecondsTimestamp: ValueTimeMark = markNow()
        private var uploadSecondsTimestamp: ValueTimeMark? = null
        private var tryOnSecondsTimestamp: ValueTimeMark? = null

        public fun setTryOnDuration(): Builder = apply {
            this.tryOnSecondsTimestamp = markNow()
        }

        public fun setUploadDuration(): Builder = apply {
            this.uploadSecondsTimestamp = markNow()
        }

        public fun build(): AiutaTryOnMetadata {
            val internalUploadSecondsTimestamp = uploadSecondsTimestamp
            val internalTryOnSecondsTimestamp = tryOnSecondsTimestamp

            // Check props
            checkNotNull(
                value = internalUploadSecondsTimestamp,
                lazyMessage = {
                    propertyNotValid(
                        property = "uploadSecondsTimestamp",
                        methodToCall = "setUploadDuration()",
                    )
                },
            )

            checkNotNull(
                value = internalTryOnSecondsTimestamp,
                lazyMessage = {
                    propertyNotValid(
                        property = "tryOnDurationSeconds",
                        methodToCall = "setTryOnDuration()",
                    )
                },
            )

            // Calculate durations
            // Start -> Upload
            val uploadDurationSeconds = internalUploadSecondsTimestamp - startSecondsTimestamp
            // Upload -> TryOn
            val tryOnDurationSeconds = internalTryOnSecondsTimestamp - internalUploadSecondsTimestamp

            return AiutaTryOnMetadata(
                startSecondsTimestamp = startSecondsTimestamp,
                uploadDurationSeconds = uploadDurationSeconds,
                tryOnDurationSeconds = tryOnDurationSeconds,
            )
        }
    }

    public companion object {
        public fun markNow(): ValueTimeMark {
            val timeSource = TimeSource.Monotonic
            return timeSource.markNow()
        }

        private fun propertyNotValid(
            property: String,
            methodToCall: String,
        ): String = """
                AiutaTryOnMetadata: $property is null, therefore cannot init AiutaTryOnMetadata.
                Please, call $methodToCall before build()
        """.trimIndent()
    }
}
