package com.jlahougue.dndcompanion.data_character_spell.data.di

import com.jlahougue.character_spell_domain.use_case.GetAllSpells
import com.jlahougue.character_spell_domain.use_case.GetCharacterSpellsStats
import com.jlahougue.character_spell_domain.use_case.GetFilteredLevels
import com.jlahougue.character_spell_domain.use_case.GetSpell
import com.jlahougue.character_spell_domain.use_case.GetSpellcasterStats
import com.jlahougue.character_spell_domain.use_case.GetSpells
import com.jlahougue.character_spell_domain.use_case.SaveSpell
import com.jlahougue.character_spell_domain.use_case.SaveSpellSlot
import com.jlahougue.character_spell_domain.use_case.SpellUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.data.repository.CharacterSpellRepository
import com.jlahougue.dndcompanion.data_character_spell.data.source.local.CharacterSpellLocalDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellRemoteDataSource

class CharacterSpellModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: CharacterSpellRemoteDataSource,
    private val localDataSource: CharacterSpellLocalDataSource
) : com.jlahougue.character_spell_domain.di.ICharacterSpellModule {

    override val repository by lazy {
        CharacterSpellRepository(
            remoteDataSource,
            localDataSource
        )
    }

    override val useCases by lazy {
        SpellUseCases(
            GetSpell(repository),
            GetFilteredLevels(repository),
            GetSpells(repository),
            GetAllSpells(repository),
            SaveSpell(
                dispatcherProvider,
                repository
            ),
            SaveSpellSlot(
                dispatcherProvider,
                repository
            ),
            GetSpellcasterStats(repository),
            GetCharacterSpellsStats(repository)
        )
    }
}