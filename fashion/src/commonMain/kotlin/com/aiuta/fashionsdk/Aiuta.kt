package com.aiuta.fashionsdk

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.logger.AiutaLogger

/**
 * Main entry point to the Aiuta Fashion SDK.
 *
 * The [Aiuta] class provides access to all SDK functionality and serves as the central
 * configuration point for authentication, platform context, and logging.
 *
 * ```kotlin
 * val aiuta = aiuta {
 *     authenticationStrategy = ApiKeyAuthenticationStrategy("your-api-key")
 *     platformContext = applicationContext // Android Context
 *     logger = MyCustomLogger()
 * }
 * ```
 *
 * @property authenticationStrategy The authentication strategy used for API requests
 * @property platformContext Platform-specific context (Android Context or platform equivalent)
 * @property logger Optional logger for SDK operations and debugging
 *
 * @see AuthenticationStrategy
 * @see AiutaPlatformContext
 * @see AiutaLogger
 */
public class Aiuta private constructor(
    public val authenticationStrategy: AuthenticationStrategy,
    public val platformContext: AiutaPlatformContext,
    public val logger: AiutaLogger?,
) {
    /**
     * Builder class for creating [Aiuta] instances with DSL-style configuration.
     *
     * This builder ensures all required properties are set before creating an [Aiuta] instance.
     *
     * ```kotlin
     * val aiuta = Aiuta.Builder()
     *     .apply {
     *         authenticationStrategy = ApiKeyAuthenticationStrategy("api-key")
     *         platformContext = context
     *         logger = customLogger
     *     }
     *     .build()
     * ```
     */
    @AiutaDsl
    public class Builder {
        /**
         * Platform-specific context required for SDK operations.
         * On Android, this should be an Android Context.
         */
        public var platformContext: AiutaPlatformContext? = null

        /**
         * Authentication strategy for API requests.
         */
        public var authenticationStrategy: AuthenticationStrategy? = null

        /**
         * Optional logger for SDK operations and debugging.
         * If not provided, no logging will be performed.
         */
        public var logger: AiutaLogger? = null

        /**
         * Creates an [Aiuta] instance with the configured properties.
         *
         * @return Configured [Aiuta] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public fun build(): Aiuta = Aiuta(
            authenticationStrategy = authenticationStrategy.checkNotNullWithDescription(
                property = "authenticationStrategy",
                methodToCall = "setAuthenticationStrategy()",
            ),
            platformContext = platformContext.checkNotNullWithDescription(
                property = "platformContext",
                methodToCall = "setPlatformContext()",
            ),
            logger = logger,
        )
    }

    private companion object {
        fun <T> T?.checkNotNullWithDescription(
            property: String,
            methodToCall: String,
        ): T = checkNotNull(
            value = this,
            lazyMessage = {
                propertyIsNull(
                    property = property,
                    methodToCall = methodToCall,
                )
            },
        )

        fun propertyIsNull(
            property: String,
            methodToCall: String,
        ): String = "Aiuta: $property is null, therefore cannot init Aiuta. " +
            "Please, call $methodToCall before build()"
    }
}

/**
 * Creates an [Aiuta] instance using DSL-style configuration.
 *
 * This is the recommended way to create [Aiuta] instances as it provides
 * a clean and readable configuration syntax.
 *
 * ```kotlin
 * val aiuta = aiuta {
 *     authenticationStrategy = ApiKeyAuthenticationStrategy("your-api-key")
 *     platformContext = applicationContext
 *     logger = AiutaLogger.create()
 * }
 * ```
 *
 * @param block Configuration block for setting up the [Aiuta] instance
 * @return Configured [Aiuta] instance
 * @throws IllegalArgumentException if required properties are not set in the block
 */
public inline fun aiuta(
    block: Aiuta.Builder.() -> Unit,
): Aiuta = Aiuta.Builder().apply(block).build()
