package com.aiuta.fashionsdk

import android.app.Application

/**
 * [Aiuta] class is an entry point to Aiuta sdk.
 *
 * @param apiKey for
 */
public class Aiuta private constructor(
    public val apiKey: String,
    public val application: Application,
) {
    /**
     * Public [Builder] for initialize [Aiuta] class
     */
    public class Builder {
        private var apiKey: String? = null
        private var application: Application? = null

        public fun setApiKey(apiKey: String): Builder {
            return apply {
                this.apiKey = apiKey
            }
        }

        public fun setApplication(context: Application): Builder {
            return apply {
                this.application = context
            }
        }

        public fun build(): Aiuta {
            val internalApiKey = apiKey
            val internalApplication = application

            // Checks for api key
            checkNotNull(
                value = internalApiKey,
                lazyMessage = { ERROR_MESSAGE_API_KEY_NULL },
            )
            check(
                value = internalApiKey.isNotEmpty(),
                lazyMessage = { ERROR_MESSAGE_API_KEY_EMPTY },
            )

            // Checks for context
            checkNotNull(
                value = internalApplication,
                lazyMessage = { ERROR_MESSAGE_CONTEXT_NULL },
            )

            return Aiuta(
                apiKey = internalApiKey,
                application = internalApplication,
            )
        }
    }

    private companion object {
        const val ERROR_MESSAGE_API_KEY_NULL =
            "Aiuta: api key is null, therefore cannot init Aiuta. " +
                "Please, call setApiKey() before build()"
        const val ERROR_MESSAGE_API_KEY_EMPTY =
            "Aiuta: api key is empty, therefore cannot init Aiuta. " +
                "Please, call setApiKey() with not empty apiKey param before build()"

        const val ERROR_MESSAGE_CONTEXT_NULL =
            "Aiuta: application context is not applied, therefore cannot init Aiuta. " +
                "Please, call setApplication() with application context param before build()"
    }
}
