package com.jlahougue.dndcompanion.data_spell.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent

interface SpellRemoteDataSource {
    suspend fun getSpells(
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellRemoteListener: SpellRemoteListener
    )
}