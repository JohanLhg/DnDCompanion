package com.jlahougue.spell_domain.repository

import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.spell_domain.model.SpellSource
import kotlinx.coroutines.flow.Flow

interface ISpellRepository {
    suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    )
    fun saveSource(source: SpellSource)
    suspend fun getIds(): List<String>
    fun getSources(): Flow<List<SpellSource>>
}