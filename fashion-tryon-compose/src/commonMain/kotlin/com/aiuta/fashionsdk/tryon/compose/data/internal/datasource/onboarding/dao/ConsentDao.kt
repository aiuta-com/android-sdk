package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding.ConsentEntity

@Dao
internal interface ConsentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(consents: List<ConsentEntity>)

    @Query("SELECT id FROM onboarding_consents")
    suspend fun getConsentIds(): List<String>

    @Query("SELECT id FROM onboarding_consents WHERE isObtained = 1")
    suspend fun getObtainedConsentIds(): List<String>
}
