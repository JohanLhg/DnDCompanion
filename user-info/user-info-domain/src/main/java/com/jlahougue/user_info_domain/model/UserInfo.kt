package com.jlahougue.user_info_domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: String? = null,
    val characterId: Long = -1L,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
