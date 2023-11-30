package com.jlahougue.dndcompanion.data_character_sheet.di

import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character_sheet.data.repository.CharacterSheetRepository
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.LoadCharacters
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository

class CharacterSheetModule(
    dispatcherProvider: DispatcherProvider,
    remoteDataSource: RemoteDataSource,
    characterRepository: ICharacterRepository,
    abilityRepository: IAbilityRepository,
    skillRepository: ISkillRepository,
    spellRepository: ISpellRepository,
    weaponRepository: IWeaponRepository
) : ICharacterSheetModule {
    override val characterSheetRepository by lazy {
        CharacterSheetRepository(remoteDataSource.characterSheetDao)
    }

    override val loadCharacterSheetUseCase by lazy {
        LoadCharacters(
            dispatcherProvider,
            characterSheetRepository,
            characterRepository,
            abilityRepository,
            skillRepository,
            spellRepository,
            weaponRepository
        )
    }
}