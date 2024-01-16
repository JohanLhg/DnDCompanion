package com.jlahougue.dndcompanion.data_user_info.domain.model

import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: String? = null,
    val characterId: Long = -1L,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
