package com.jlahougue.dndcompanion.data_ability.domain.repository

import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

interface IAbilityRepository {
    suspend fun create(characterID: Long)
    suspend fun save(ability: Ability)
    suspend fun saveToLocal(ability: Ability)
}