package com.jlahougue.dndcompanion.data_health.domain.use_case

data class HealthUseCases(
    val saveHealth: SaveHealth,
    val saveDeathSaves: SaveDeathSaves,
    val getHealth: GetHealth,
    val getDeathSaves: GetDeathSaves
)