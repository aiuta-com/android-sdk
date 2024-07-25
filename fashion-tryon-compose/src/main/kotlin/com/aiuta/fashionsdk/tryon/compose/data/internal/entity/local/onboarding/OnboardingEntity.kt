package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "onboarding_checker")
internal class OnboardingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
