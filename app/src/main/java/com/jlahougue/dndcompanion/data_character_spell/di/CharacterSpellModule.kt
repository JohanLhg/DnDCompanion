package com.jlahougue.dndcompanion.data_character_spell.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_character_spell.data.repository.CharacterSpellRepository
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetAllSpells
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetCharacterSpellsStats
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetFilteredLevels
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetSpellcasterStats
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.GetSpells
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SaveSpell
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SaveSpellSlot
import com.jlahougue.dndcompanion.data_character_spell.domain.use_case.SpellUseCases

class CharacterSpellModule(
    private val dispatcherProvider: DispatcherProvider,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ICharacterSpellModule {

    override val repository by lazy {
        CharacterSpellRepository(
            remoteDataSource.characterSpellDao,
            localDataSource.characterSpellDao()
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