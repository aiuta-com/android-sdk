package com.aiuta.fashionsdk

import android.app.Application

/**
 * [Fashion] class is an entry point to Fashion sdk.
 *
 * @param apiKey for
 */
public class Fashion private constructor(
    public val apiKey: String,
    public val application: Application,
) {
    /**
     * Public [Builder] for initialize [Fashion] class
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

        public fun build(): Fashion {
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

            return Fashion(
                apiKey = internalApiKey,
                application = internalApplication,
            )
        }
    }

    private companion object {
        const val ERROR_MESSAGE_API_KEY_NULL =
            "Fashion: api key is null, therefore cannot init Fashion. " +
                "Please, call setApiKey() before build()"
        const val ERROR_MESSAGE_API_KEY_EMPTY =
            "Fashion: api key is empty, therefore cannot init Fashion. " +
                "Please, call setApiKey() with not empty apiKey param before build()"

        const val ERROR_MESSAGE_CONTEXT_NULL =
            "Fashion: application context is not applied, therefore cannot init Fashion. " +
                "Please, call setApplication() with application context param before build()"
    }
}
