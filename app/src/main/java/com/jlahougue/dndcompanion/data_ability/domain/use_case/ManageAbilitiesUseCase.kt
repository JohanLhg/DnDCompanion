package com.jlahougue.dndcompanion.data_ability.domain.use_case

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityView
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest

class ManageAbilitiesUseCase(
    private val userInfoRepository: IUserInfoRepository,
    private val abilityRepository: IAbilityRepository
) {

    private val _abilities = MutableStateFlow(listOf<AbilityView>())
    val abilities = _abilities.asStateFlow()

    suspend operator fun invoke() {
        userInfoRepository.get().collectLatest {
            abilityRepository.get(it.characterId).collect { abilities ->
                _abilities.value = abilities
            }
        }
    }
}