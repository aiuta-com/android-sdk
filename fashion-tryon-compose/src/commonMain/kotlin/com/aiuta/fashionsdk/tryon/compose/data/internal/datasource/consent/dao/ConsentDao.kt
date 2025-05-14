package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.onboarding.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.consent.ObtainedConsentEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface ConsentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entities: List<ObtainedConsentEntity>)

    @Query("SELECT * FROM obtained_consents")
    fun getObtainedConsentsFlow(): Flow<List<ObtainedConsentEntity>>
}
