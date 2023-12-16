package com.jlahougue.dndcompanion.data_character_spell.domain.use_case

import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ManageSpellsUseCase {

    private val _spells = MutableStateFlow<List<SpellInfo>>(listOf())
    private val spells = _spells.asStateFlow()
}