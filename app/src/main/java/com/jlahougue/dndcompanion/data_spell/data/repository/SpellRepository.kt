package com.jlahougue.dndcompanion.data_spell.data.repository

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellClassLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellDamageTypeLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteListener
import com.jlahougue.dndcompanion.data_spell.domain.model.Spell
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellClass
import com.jlahougue.dndcompanion.data_spell.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository

class SpellRepository(
    private val spellRemoteDataSource: SpellRemoteDataSource,
    private val spellLocalDataSource: SpellLocalDataSource,
    private val spellClassLocalDataSource: SpellClassLocalDataSource,
    private val spellDamageTypeLocalDataSource: SpellDamageTypeLocalDataSource
): ISpellRepository, SpellRemoteListener {

    override suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingSpellIds = getIds()
        spellRemoteDataSource.getSpells(
            existingSpellIds,
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(spell: Spell): Boolean {
        return spellLocalDataSource.insert(spell) != -1L
    }

    override suspend fun saveClasses(spellClasses: List<SpellClass>) {
        spellClassLocalDataSource.insert(spellClasses)
    }

    override suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>) {
        spellDamageTypeLocalDataSource.insert(spellDamageTypes)
    }

    override suspend fun getIds(): List<String> {
        return spellLocalDataSource.getIds()
    }
}