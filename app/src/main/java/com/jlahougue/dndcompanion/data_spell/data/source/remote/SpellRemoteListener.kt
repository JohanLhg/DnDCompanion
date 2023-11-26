package com.jlahougue.dndcompanion.data_spell.data.source.remote

import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType

interface SpellRemoteListener {
    suspend fun save(spell: Spell): Boolean
    suspend fun saveClasses(spellClasses: List<SpellClass>)
    suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>)
}