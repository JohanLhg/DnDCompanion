package com.jlahougue.spell_data.source.remote

import com.jlahougue.core_domain.util.ApiEvent

interface SpellRemoteDataSource {
    suspend fun load(
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellRemoteListener: SpellRemoteListener
    )
}