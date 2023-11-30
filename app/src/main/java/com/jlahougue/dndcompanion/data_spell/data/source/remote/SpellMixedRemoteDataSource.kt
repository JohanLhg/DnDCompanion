package com.jlahougue.dndcompanion.data_spell.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.data_spell.data.source.remote.subsource.SpellFirebaseDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.subsource.SpellOpen5eDataSource
import com.jlahougue.dndcompanion.data_spell.domain.model.CharacterSpell

class SpellMixedRemoteDataSource(
    private val spellFirebaseDataSource: SpellFirebaseDataSource,
    private val spellOpen5eDataSource: SpellOpen5eDataSource
): SpellRemoteDataSource {
    override suspend fun load(
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellRemoteListener: SpellRemoteListener
    ) = spellOpen5eDataSource.load(
        existingSpellIds,
        existingDamageTypes,
        onApiEvent,
        spellRemoteListener
    )

    override fun save(characterSpell: CharacterSpell) {
        spellFirebaseDataSource.save(characterSpell)
    }
}