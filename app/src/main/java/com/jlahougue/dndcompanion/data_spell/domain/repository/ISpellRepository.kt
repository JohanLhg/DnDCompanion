package com.jlahougue.dndcompanion.data_spell.domain.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType

interface ISpellRepository {
    suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    )
    suspend fun save(spell: Spell): Boolean
    suspend fun saveClasses(spellClasses: List<SpellClass>)
    suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>)
    suspend fun getIds(): List<String>
}