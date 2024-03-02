package com.jlahougue.stats_presentation

sealed class StatsEvent {
    data class OnArmorClassChanged(val armorClass: Int) : StatsEvent()
    data class OnSpeedChanged(val speed: Int) : StatsEvent()
}