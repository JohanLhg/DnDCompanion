package com.jlahougue.spell_domain.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType

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