package com.aiuta.fashionsdk.network.exceptions

/**
 * Exception thrown when the network is disconnected or unavailable.
 *
 * This exception extends [IllegalStateException] and is used to indicate
 * that a network operation could not be completed due to a lack of connectivity.
 */
public class FashionNetworkDisconnectedException : IllegalStateException()
