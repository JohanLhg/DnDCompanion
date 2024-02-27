package com.jlahougue.spell_data.source.remote

import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType

interface SpellRemoteListener {
    suspend fun save(spell: Spell): Boolean
    suspend fun saveClasses(spellClasses: List<SpellClass>)
    suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>)
}