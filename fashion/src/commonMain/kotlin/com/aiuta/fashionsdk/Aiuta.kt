package com.aiuta.fashionsdk

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.authentication.AuthenticationStrategy
import com.aiuta.fashionsdk.context.AiutaPlatformContext

/**
 * [Aiuta] class is an entry point to Aiuta sdk.
 */
public class Aiuta private constructor(
    public val authenticationStrategy: AuthenticationStrategy,
    public val platformContext: AiutaPlatformContext,
) {
    /**
     * Public [Builder] for initialize [Aiuta] class
     */
    @AiutaDsl
    public class Builder {
        public var platformContext: AiutaPlatformContext? = null
        public var authenticationStrategy: AuthenticationStrategy? = null

        public fun build(): Aiuta = Aiuta(
            authenticationStrategy = authenticationStrategy.checkNotNullWithDescription(
                property = "authenticationStrategy",
                methodToCall = "setAuthenticationStrategy()",
            ),
            platformContext = platformContext.checkNotNullWithDescription(
                property = "platformContext",
                methodToCall = "setPlatformContext()",
            ),
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

public inline fun aiuta(
    block: Aiuta.Builder.() -> Unit,
): Aiuta = Aiuta.Builder().apply(block).build()
