package com.aiuta.fashionsdk.tryon.compose.data.internal.repository.base

import com.aiuta.fashionsdk.tryon.compose.domain.internal.time.TimeSaver
import kotlin.time.Duration
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

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
        delay: Duration,
        timeKey: String = key + SAVE_TIME_SUFFIX,
        forceUpdate: Boolean = false,
        remoteLoad: suspend () -> T,
        localLoad: suspend () -> T?,
        replaceLocalData: suspend (T) -> Unit,
    ): T {
        mutex.withLock {
            val timeLimitPassed = isPassedTimeLimit(timeKey, delay)
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
     * Check is [delay] time passed
     *
     * @return true, if [delay] is LOWER than delta between current time and last saved by [timeKey]
     * @return false, if [delay] is BIGGER OR EQUAL than delta between current time and last saved by [timeKey]
     * @return null, if time has not saved yet
     */
    private suspend fun isPassedTimeLimit(
        timeKey: String,
        delay: Duration,
    ): Boolean? {
        val savedTime =
            timeSaver
                .getLastSavedTime(timeKey)
                ?.toInstant(TimeZone.currentSystemDefault())
                ?: return null

        val currentTime = timeSaver.getCurrentTime().toInstant(TimeZone.currentSystemDefault())
        val delta = currentTime - savedTime

        return delta > delay
    }

    private companion object {
        const val SAVE_TIME_SUFFIX = "_saved_time"
    }
}
