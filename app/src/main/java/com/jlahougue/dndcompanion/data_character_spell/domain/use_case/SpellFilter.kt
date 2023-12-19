package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

sealed class SpellFilter {
    data object All : SpellFilter()
    data object Known : SpellFilter()
    data object Prepared : SpellFilter()
}