package com.aiuta.fashionsdk

import android.app.Application
import com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy

/**
 * [Aiuta] class is an entry point to Aiuta sdk.
 */
public class Aiuta private constructor(
    public val subscriptionId: String,
    public val authenticationStrategy: AuthenticationStrategy,
    public val application: Application,
) {
    /**
     * Public [Builder] for initialize [Aiuta] class
     */
    public class Builder {
        private var application: Application? = null
        private var authenticationStrategy: AuthenticationStrategy? = null
        private var subscriptionId: String? = null

        @Deprecated(
            message = "Apply ApiKeyAuthenticationStrategy instead",
            replaceWith =
                ReplaceWith(
                    expression =
                        "setAuthenticationStrategy(" +
                            "authenticationStrategy = ApiKeyAuthenticationStrategy(apiKey = apiKey)" +
                            ")",
                    imports =
                        arrayOf(
                            "com.aiuta.fashionsdk.authentication.ApiKeyAuthenticationStrategy",
                        ),
                ),
        )
        public fun setApiKey(apiKey: String): Builder {
            return apply {
                this.authenticationStrategy =
                    ApiKeyAuthenticationStrategy(
                        apiKey = apiKey,
                    )
            }
        }

        public fun setApplication(context: Application): Builder {
            return apply {
                this.application = context
            }
        }

        public fun setAuthenticationStrategy(
            authenticationStrategy: AuthenticationStrategy,
        ): Builder {
            return apply {
                this.authenticationStrategy = authenticationStrategy
            }
        }

        public fun setSubscriptionId(subscriptionId: String): Builder {
            return apply {
                this.subscriptionId = subscriptionId
            }
        }

        public fun build(): Aiuta {
            val internalApplication = application
            val internalAuthenticationStrategy = authenticationStrategy
            val internalSubscriptionId = subscriptionId

            // Checks for context
            checkNotNull(
                value = internalApplication,
                lazyMessage = {
                    propertyIsNull(
                        property = "application",
                        methodToCall = "setApplication()",
                    )
                },
            )

            // Checks for authentication strategy
            checkNotNull(
                value = internalAuthenticationStrategy,
                lazyMessage = {
                    propertyIsNull(
                        property = "authenticationStrategy",
                        methodToCall = "setAuthenticationStrategy()",
                    )
                },
            )

            // Checks for subscription id
            checkNotNull(
                value = internalSubscriptionId,
                lazyMessage = {
                    propertyIsNull(
                        property = "subscriptionId",
                        methodToCall = "setSubscriptionId()",
                    )
                },
            )

            check(
                value = internalSubscriptionId.isNotEmpty(),
                lazyMessage = {
                    propertyIsEmpty(
                        property = "subscriptionId",
                        methodToCall = "setSubscriptionId()",
                    )
                },
            )

            return Aiuta(
                application = internalApplication,
                authenticationStrategy = internalAuthenticationStrategy,
                subscriptionId = internalSubscriptionId,
            )
        }
    }

    private companion object {
        fun propertyIsEmpty(
            property: String,
            methodToCall: String,
        ): String {
            return "Aiuta: $property is empty, therefore cannot init Aiuta. " +
                "Please, call $methodToCall with not empty $property param before build()"
        }

        fun propertyIsNull(
            property: String,
            methodToCall: String,
        ): String {
            return "Aiuta: $property is null, therefore cannot init Aiuta. " +
                "Please, call $methodToCall before build()"
        }
    }
}
