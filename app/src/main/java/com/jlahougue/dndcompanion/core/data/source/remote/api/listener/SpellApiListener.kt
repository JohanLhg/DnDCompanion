package com.jlahougue.dndcompanion.core.data.source.remote.api.listener

import com.jlahougue.dndcompanion.core.domain.model.Spell
import com.jlahougue.dndcompanion.core.domain.model.SpellClass
import com.jlahougue.dndcompanion.core.domain.model.SpellDamageType

interface SpellApiListener {
    suspend fun save(spell: Spell): Boolean
    suspend fun saveClasses(spellClasses: List<SpellClass>)
    suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>)
}