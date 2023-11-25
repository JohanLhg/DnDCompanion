package com.jlahougue.dndcompanion.core.data.repository

import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellClassDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellDamageTypeDao
import com.jlahougue.dndcompanion.core.data.source.local.dao.SpellDao
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.api.listener.SpellApiListener
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.model.Spell
import com.jlahougue.dndcompanion.core.domain.model.SpellClass
import com.jlahougue.dndcompanion.core.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.core.domain.repository.ISpellRepository

class SpellRepository(
    private val spellApiDao: SpellApiDao,
    private val spellDao: SpellDao,
    private val spellClassDao: SpellClassDao,
    private val spellDamageTypeDao: SpellDamageTypeDao
): ISpellRepository, SpellApiListener {

    override suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingSpellIds = getIds()
        spellApiDao.getSpells(
            existingSpellIds,
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(spell: Spell): Boolean {
        return spellDao.insert(spell) != -1L
    }

    override suspend fun saveClasses(spellClasses: List<SpellClass>) {
        spellClassDao.insert(spellClasses)
    }

    override suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>) {
        spellDamageTypeDao.insert(spellDamageTypes)
    }

    override suspend fun getIds(): List<String> {
        return spellDao.getIds()
    }
}