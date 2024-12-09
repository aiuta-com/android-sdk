package com.aiuta.fashionsdk.tryon.core.domain.slice.ping.controller.internal.utils

/**
 * Delay before getting operation status in second:
 * 1 (4 times), 0.5 (20 times), 3 (forever)
 */

internal fun getGenerationDelaySequence(): Sequence<Long> {
    return sequence {
        // 4 times by 1 sec
        (0..3).forEach {
            yield(1000L)
        }

        // 20 times by 0.5 sec
        (0..19).forEach {
            yield(500L)
        }

        // Then forever 3
        while (true) {
            yield(3000L)
        }
    }
}
