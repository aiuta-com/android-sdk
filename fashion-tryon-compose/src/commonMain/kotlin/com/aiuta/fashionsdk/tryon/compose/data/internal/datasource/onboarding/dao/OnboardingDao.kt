package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.OnboardingEntity

@Dao
internal interface OnboardingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: OnboardingEntity)

    @Query("SELECT * FROM onboarding_state LIMIT 1")
    suspend fun getOnboardingEntity(): OnboardingEntity?
}
