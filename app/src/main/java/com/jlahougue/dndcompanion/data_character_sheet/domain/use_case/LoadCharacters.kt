package com.jlahougue.dndcompanion.data_character_sheet.domain.use_case

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_character.domain.repository.ICharacterRepository
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseEvent
import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository
import com.jlahougue.dndcompanion.data_spell.domain.repository.ISpellRepository
import com.jlahougue.dndcompanion.data_weapon.domain.repository.IWeaponRepository
import com.jlahougue.dndcompanion.feature_loading_data.domain.use_case.LoadFromRemote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadCharacters(
    private val dispatcherProvider: DispatcherProvider,
    private val repository: ICharacterSheetRepository,
    private val characterRepository: ICharacterRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val spellRepository: ISpellRepository,
    private val weaponRepository: IWeaponRepository
) : LoadFromRemote(UiText.StringResource(R.string.loading_characters)) {

    override fun invoke() {
        CoroutineScope(dispatcherProvider.io).launch {
            repository.load(::onEvent)
        }
    }

    fun onEvent(event: CharacterSheetFirebaseEvent) {
        when (event) {
            is CharacterSheetFirebaseEvent.Canceled -> {
                onApiEvent(ApiEvent.Error(UiText.StringResource(R.string.error_fetching_characters)))
            }
            is CharacterSheetFirebaseEvent.Failure -> {
                onApiEvent(ApiEvent.Error(event.message))
            }
            is CharacterSheetFirebaseEvent.Success -> {
                CoroutineScope(dispatcherProvider.io).launch {
                    onApiEvent(ApiEvent.SetMaxProgress(event.characterSheets.size))
                    for (characterSheet in event.characterSheets) {
                        if (characterSheet.character == null) continue
                        characterRepository.saveToLocal(characterSheet.character!!)
                        abilityRepository.saveToLocal(characterSheet.abilities.values.toList())
                        skillRepository.saveToLocal(characterSheet.skills.values.toList())
                        spellRepository.saveToLocal(characterSheet.spells.values.toList())
                        weaponRepository.saveToLocal(characterSheet.weapons.values.toList())
                        onApiEvent(ApiEvent.UpdateProgress)
                    }
                    onApiEvent(ApiEvent.Finish)
                }
            }
        }
    }
}