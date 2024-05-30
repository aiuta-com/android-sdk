package com.aiuta.fashionsdk.tryon.compose.data.internal.repository.base

import com.aiuta.fashionsdk.tryon.compose.domain.internal.time.TimeSaver
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal abstract class BaseRepository(
    private val key: String,
    private val timeSaver: TimeSaver,
) {
    private val mutex: Mutex = Mutex()

    /**
     * Method for loading data depends on expiration time [delay]
     *
     * @throws NullPointerException if we can not get data from backend,
     * and local data is empty
     */
    suspend fun <T> updatableLoad(
        delayMilliseconds: Long,
        timeKey: String = key + SAVE_TIME_SUFFIX,
        forceUpdate: Boolean = false,
        remoteLoad: suspend () -> T,
        localLoad: suspend () -> T?,
        replaceLocalData: suspend (T) -> Unit,
    ): T {
        mutex.withLock {
            val timeLimitPassed = isPassedTimeLimit(timeKey, delayMilliseconds)
            val timeHasPassed = timeLimitPassed == null || timeLimitPassed == true

            return try {
                if (timeHasPassed || forceUpdate) {
                    // It is first time or time has passed
                    // Let's load from remote

                    val result = remoteLoad()
                    replaceLocalData(result)
                    timeSaver.saveCurrentTime(timeKey)
                    result
                } else {
                    // Time has not passed, let's load from local
                    checkNotNull(localLoad())
                }
            } catch (e: Exception) {
                // Fallback to local
                checkNotNull(localLoad())
            }
        }
    }

    /**
     * Check is [delayMilliseconds] time passed
     *
     * @return true, if [delayMilliseconds] is LOWER than delta between current time and last saved by [timeKey]
     * @return false, if [delayMilliseconds] is BIGGER OR EQUAL than delta between current time and last saved by [timeKey]
     * @return null, if time has not saved yet
     */
    private suspend fun isPassedTimeLimit(
        timeKey: String,
        delayMilliseconds: Long,
    ): Boolean? {
        val savedTime =
            timeSaver
                .getLastSavedTime(timeKey)
                ?: return null

        val currentTime = timeSaver.getCurrentTime()
        val delta = currentTime.time - savedTime.time

        return delta > delayMilliseconds
    }

    private companion object {
        const val SAVE_TIME_SUFFIX = "_saved_time"
    }
}
