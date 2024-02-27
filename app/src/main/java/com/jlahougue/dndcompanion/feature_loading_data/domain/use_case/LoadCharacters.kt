package com.jlahougue.dndcompanion.feature_loading_data.domain.use_case

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseEvent
import com.jlahougue.dndcompanion.data_character_sheet.domain.repository.ICharacterSheetRepository
import com.jlahougue.dndcompanion.data_character_sheet.domain.use_case.CharacterSheetUseCases
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.stats_domain.repository.IStatsRepository
import com.jlahougue.weapon_domain.repository.IWeaponRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LoadCharacters(
    private val dispatcherProvider: DispatcherProvider,
    private val characterSheetRepository: ICharacterSheetRepository,
    private val characterSheetUseCases: CharacterSheetUseCases,
    private val characterRepository: ICharacterRepository,
    private val abilityRepository: IAbilityRepository,
    private val skillRepository: ISkillRepository,
    private val statsRepository: IStatsRepository,
    private val healthRepository: IHealthRepository,
    private val characterSpellRepository: ICharacterSpellRepository,
    private val weaponRepository: IWeaponRepository,
    private val moneyRepository: IMoneyRepository,
    private val itemRepository: IItemRepository
) : LoadFromRemote(UiText.StringResource(R.string.loading_characters)) {

    override fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            characterSheetRepository.load(::onEvent)
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
                    var noneExist = !characterRepository.exists()
                    for (characterSheet in event.characterSheets) {
                        characterRepository.saveToLocal(characterSheet.character)
                        abilityRepository.saveToLocal(characterSheet.abilities.values.toList())
                        skillRepository.saveToLocal(characterSheet.skills.values.toList())
                        statsRepository.saveToLocal(characterSheet.stats)
                        healthRepository.saveToLocal(characterSheet.health)
                        healthRepository.saveToLocal(characterSheet.deathSaves)
                        characterSpellRepository.saveSpellSlotsToLocal(
                            characterSheet.spellSlots.map { (key, value) ->
                                SpellSlot(characterSheet.id, key.toInt(), value)
                            }
                        )
                        characterSpellRepository.saveToLocal(characterSheet.spells.values.toList())
                        weaponRepository.saveToLocal(characterSheet.weapons.values.toList())
                        moneyRepository.saveToLocal(characterSheet.money)
                        itemRepository.saveToLocal(characterSheet.items.values.toList())
                        noneExist = false
                        onApiEvent(ApiEvent.UpdateProgress)
                    }
                    if (noneExist) characterSheetUseCases.createCharacter()
                    onApiEvent(ApiEvent.Finish)
                }
            }
        }
    }
}