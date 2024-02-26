package com.jlahougue.user_info_domain.model

import com.jlahougue.settings_domain.model.UnitSystem
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: String? = null,
    val characterId: Long = -1L,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
