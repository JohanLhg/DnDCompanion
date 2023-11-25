package com.jlahougue.dndcompanion.feature_loading_screen.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.api.listener.SpellApiListener
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.model.Spell
import com.jlahougue.dndcompanion.core.domain.model.SpellClass
import com.jlahougue.dndcompanion.core.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.core.domain.repository.ISpellRepository

class FakeSpellRepository(
    private val spellApiDao: SpellApiDao
): ISpellRepository, SpellApiListener {

    private val spells = mutableListOf<Spell>()
    private val spellClasses = mutableListOf<SpellClass>()
    private val spellDamageTypes = mutableListOf<SpellDamageType>()

    override suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        spellApiDao.getSpells(
            getIds(),
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(spell: Spell): Boolean {
        spells.add(spell)
        return true
    }

    override suspend fun saveClasses(spellClasses: List<SpellClass>) {
        this.spellClasses.addAll(spellClasses)
    }

    override suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>) {
        this.spellDamageTypes.addAll(spellDamageTypes)
    }

    override suspend fun getIds(): List<String> {
        return spells.map { it.id }
    }
}