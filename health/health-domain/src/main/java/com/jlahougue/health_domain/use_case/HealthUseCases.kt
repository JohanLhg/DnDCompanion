package com.jlahougue.health_domain.use_case

data class HealthUseCases(
    val saveHealth: SaveHealth,
    val saveDeathSaves: SaveDeathSaves,
    val getHealth: GetHealth,
    val getHitDice: GetHitDice,
    val getDeathSaves: GetDeathSaves
)