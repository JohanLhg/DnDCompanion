package com.jlahougue.character_spell_data

import com.jlahougue.character_spell_domain.di.ICharacterSpellModule
import com.jlahougue.character_spell_domain.use_case.GetAllSpells
import com.jlahougue.character_spell_domain.use_case.GetCharacterSpellsStats
import com.jlahougue.character_spell_domain.use_case.GetFilteredLevels
import com.jlahougue.character_spell_domain.use_case.GetSpell
import com.jlahougue.character_spell_domain.use_case.GetSpellcasterStats
import com.jlahougue.character_spell_domain.use_case.GetSpells
import com.jlahougue.character_spell_domain.use_case.SaveSpell
import com.jlahougue.character_spell_domain.use_case.SaveSpellSlot
import com.jlahougue.character_spell_domain.use_case.SpellUseCases
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider

class CharacterSpellModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteUserDataSource,
    localDataSource: CharacterSpellLocalDataSource
) : ICharacterSpellModule {

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