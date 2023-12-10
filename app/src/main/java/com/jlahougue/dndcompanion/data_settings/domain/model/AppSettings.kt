package com.jlahougue.dndcompanion.data_settings.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val language: Language = Language.ENGLISH,
    val unitSystem: UnitSystem = UnitSystem.METRIC
)
