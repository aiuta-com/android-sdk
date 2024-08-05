package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal.utils

/**
 * Delay before getting operation status in second:
 * 1, 1, 0.5 (12 times), 3 (forever)
 */

internal fun getGenerationDelaySequence(): Sequence<Long> {
    return sequence {
        // Two times by 1 sec
        yield(1000L)
        yield(1000L)

        // 12 times by 0.5 sec
        (0..11).forEach {
            yield(500L)
        }

        // Then forever 3
        while (true) {
            yield(3000L)
        }
    }
}
