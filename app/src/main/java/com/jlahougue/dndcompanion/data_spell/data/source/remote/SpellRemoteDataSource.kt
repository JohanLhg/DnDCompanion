package com.jlahougue.dndcompanion.data_spell.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent

interface SpellRemoteDataSource {
    suspend fun load(
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellRemoteListener: SpellRemoteListener
    )
}