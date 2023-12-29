package com.jlahougue.dndcompanion.data_character_spell.domain.repository

import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.model.CharacterSpellsStatsView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellInfo
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellSlotView
import com.jlahougue.dndcompanion.data_character_spell.domain.model.SpellcasterView
import kotlinx.coroutines.flow.Flow

interface ICharacterSpellRepository {
    suspend fun save(characterSpell: CharacterSpell)
    suspend fun saveToLocal(characterSpells: List<CharacterSpell>)
    suspend fun saveSpellSlotsToLocal(spellSlots: List<SpellSlot>)
    fun getAllSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>
    fun getKnownSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>
    fun getPreparedSpells(characterId: Long): Flow<Map<SpellSlotView, List<SpellInfo>>>
    fun getSpellcasterStats(characterId: Long): Flow<SpellcasterView>
    fun getCharacterSpellsStats(characterId: Long): Flow<CharacterSpellsStatsView>
}