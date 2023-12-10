package com.jlahougue.dndcompanion.data_stats.presentation

sealed class StatsEvent {
    data class OnArmorClassChange(val armorClass: Int) : StatsEvent()
    data class OnSpeedChange(val speed: Int) : StatsEvent()
}