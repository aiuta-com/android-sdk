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
        remoteLoad: suspend (forceLoad: Boolean) -> T,
        localLoad: suspend () -> T?,
        replaceLocalData: suspend (T) -> Unit,
    ): T {
        mutex.withLock {
            val timeLimitPassed = isPassedTimeLimit(timeKey, delayMilliseconds)
            val timeHasPassed = timeLimitPassed == null || timeLimitPassed == true

            val backendLoad: suspend (forceLoad: Boolean) -> T = { forceLoad ->
                val result = remoteLoad(forceLoad)
                replaceLocalData(result)
                timeSaver.saveCurrentTime(timeKey)
                result
            }

            return try {
                if (timeHasPassed || forceUpdate) {
                    // It is first time or time has passed
                    // Let's load from remote

                    backendLoad(forceUpdate)
                } else {
                    try {
                        // Time has not passed, let's load from local
                        checkNotNull(localLoad())
                    } catch (e: Exception) {
                        // Failed to get local, let's force load from backend
                        backendLoad(true)
                    }
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
