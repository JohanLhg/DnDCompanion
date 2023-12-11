package com.jlahougue.dndcompanion.data_health.domain.use_case

sealed class HealthEvent {
    data class OnMaxHealthChange(val maxHealth: Int) : HealthEvent()
    data class OnCurrentHealthChange(val currentHealth: Int) : HealthEvent()
    data class OnTemporaryHealthChange(val temporaryHealth: Int) : HealthEvent()
    data class OnHitDiceChange(val hitDice: String) : HealthEvent()
    data class OnDeathSavesSuccessChange(val successes: Int) : HealthEvent()
    data class OnDeathSavesFailureChange(val failures: Int) : HealthEvent()
}