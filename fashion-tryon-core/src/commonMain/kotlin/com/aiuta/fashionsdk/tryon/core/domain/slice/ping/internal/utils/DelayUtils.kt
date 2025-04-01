package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.internal.utils

/**
 * Delay before getting operation status in second:
 * 1 (4 times), 0.5 (20 times), 3 (forever)
 */

internal fun defaultGenerationDelaySequence(): Sequence<Long> = sequence {
    // 4 times by 1 sec
    repeat(4) {
        yield(1000L)
    }

    // 20 times by 0.5 sec
    repeat(20) {
        yield(500L)
    }

    // Then forever 3
    while (true) {
        yield(3000L)
    }
}
