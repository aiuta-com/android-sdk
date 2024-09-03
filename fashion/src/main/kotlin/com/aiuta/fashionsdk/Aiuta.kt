package com.aiuta.fashionsdk

import android.app.Application
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.authentication.OAuthAuthenticationStrategy
import java.util.UUID

/**
 * [Aiuta] class is an entry point to Aiuta sdk.
 */
public class Aiuta private constructor(
    @Deprecated(
        message = "Will be empty, because we can guarantee that this key will be used" +
            "as way to authenticate SDK, so use authenticationStrategy instead",
    )
    public val apiKey: String,
    public val authenticationStrategy: AuthenticationStrategy,
    public val application: Application,
) {
    public val uniqueId: String by lazy {
        when (authenticationStrategy) {
            is ApiKeyAuthenticationStrategy -> authenticationStrategy.apiKey
            is OAuthAuthenticationStrategy -> authenticationStrategy.clientId
        }
    }


    /**
     * Public [Builder] for initialize [Aiuta] class
     */
    public class Builder {
        private var application: Application? = null
        private var authenticationStrategy: AuthenticationStrategy? = null

        @Deprecated(
            message = "Apply ApiKeyAuthenticationStrategy instead",
            replaceWith = ReplaceWith(
                expression = "setAuthenticationStrategy(" +
                    "authenticationStrategy = ApiKeyAuthenticationStrategy(apiKey = apiKey)" +
                    ")",
                imports = arrayOf("com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy"),
            ),
        )
        public fun setApiKey(apiKey: String): Builder {
            return apply {
                this.authenticationStrategy = ApiKeyAuthenticationStrategy(
                    apiKey = apiKey,
                )
            }
        }

        public fun setAuthenticationStrategy(
            authenticationStrategy: AuthenticationStrategy,
        ): Builder {
            return apply {
                this.authenticationStrategy = authenticationStrategy
            }
        }

        public fun setApplication(context: Application): Builder {
            return apply {
                this.application = context
            }
        }

        public fun build(): Aiuta {
            val internalAuthenticationStrategy = authenticationStrategy
            val internalApplication = application

            // Checks for authentication strategy
            checkNotNull(
                value = internalAuthenticationStrategy,
                lazyMessage = { ERROR_MESSAGE_API_KEY_NULL },
            )

            // Checks for context
            checkNotNull(
                value = internalApplication,
                lazyMessage = { ERROR_MESSAGE_CONTEXT_NULL },
            )

            return Aiuta(
                apiKey = "",
                authenticationStrategy = internalAuthenticationStrategy,
                application = internalApplication,
            )
        }
    }

    private companion object {
        const val ERROR_MESSAGE_API_KEY_NULL =
            "Aiuta: authentication strategy is null, therefore cannot init Aiuta. " +
                "Please, call setAuthenticationStrategy() before build()"

        const val ERROR_MESSAGE_CONTEXT_NULL =
            "Aiuta: application context is not applied, therefore cannot init Aiuta. " +
                "Please, call setApplication() with application context param before build()"
    }
}
