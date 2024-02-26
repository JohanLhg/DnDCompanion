package com.jlahougue.dndcompanion.data_spell.data.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteListener
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.spell_domain.repository.ISpellRepository

class SpellRepository(
    private val remoteDataSource: SpellRemoteDataSource,
    private val localDataSource: SpellLocalDataSource
): ISpellRepository, SpellRemoteListener {

    override suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingSpellIds = getIds()
        remoteDataSource.load(
            existingSpellIds,
            existingDamageTypes,
            onApiEvent,
            this
        )
    }

    override suspend fun save(spell: Spell): Boolean {
        return localDataSource.insert(spell) != -1L
    }

    override suspend fun saveClasses(spellClasses: List<SpellClass>) {
        localDataSource.insertClasses(spellClasses)
    }

    override suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>) {
        localDataSource.insertDamageTypes(spellDamageTypes)
    }

    override suspend fun getIds(): List<String> {
        return localDataSource.getIds()
    }
}