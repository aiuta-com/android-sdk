package com.aiuta.fashionsdk.network.exceptions

import kotlinx.io.IOException

/**
 * Exception for handling network communication termination
 */
public class FashionNetworkIsDisconnected(
    override val cause: Throwable?,
) : IOException()
