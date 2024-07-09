package com.jlahougue.loading_domain.use_case

import com.jlahougue.ability_domain.repository.IAbilityRepository
import com.jlahougue.character_domain.repository.ICharacterRepository
import com.jlahougue.character_sheet_domain.model.CharacterSheet
import com.jlahougue.character_sheet_domain.repository.ICharacterSheetRepository
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.character_spell_domain.model.SpellSlot
import com.jlahougue.character_spell_domain.repository.ICharacterSpellRepository
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.health_domain.repository.IHealthRepository
import com.jlahougue.item_domain.repository.IItemRepository
import com.jlahougue.loading_domain.util.LoaderKey
import com.jlahougue.money_domain.repository.IMoneyRepository
import com.jlahougue.note.domain.repository.INoteRepository
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
    private val itemRepository: IItemRepository,
    private val noteRepository: INoteRepository
) : LoadFromRemote(LoaderKey.CHARACTERS) {

    override fun invoke() {
        super.invoke()
        CoroutineScope(dispatcherProvider.io).launch {
            characterSheetRepository.load(::onResult)
        }
    }

    private fun onResult(result: Result<List<CharacterSheet>, RemoteReadError>) {
        when (result) {
            is Result.Success -> {
                CoroutineScope(dispatcherProvider.io).launch {
                    onApiEvent(ApiEvent.SetMaxProgress(result.data.size))
                    var noneExist = !characterRepository.exists()
                    for (characterSheet in result.data) {
                        characterRepository.saveToLocal(characterSheet.character)
                        if (characterSheet.abilities.isEmpty()) {
                            abilityRepository.create(characterSheet.id)
                        } else {
                            abilityRepository.saveToLocal(characterSheet.abilities.values.toList())
                        }
                        if (characterSheet.skills.isEmpty()) {
                            skillRepository.create(characterSheet.id)
                        } else {
                            skillRepository.saveToLocal(characterSheet.skills.values.toList())
                        }
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
                        noteRepository.saveToLocal(characterSheet.notes.values.toList())
                        noneExist = false
                        onApiEvent(ApiEvent.UpdateProgress)
                    }
                    if (noneExist) characterSheetUseCases.createCharacter()
                    onApiEvent(ApiEvent.Finish)
                }
            }

            is Result.Failure -> {
                onApiEvent(ApiEvent.Error(result.error))
            }
        }
    }
}