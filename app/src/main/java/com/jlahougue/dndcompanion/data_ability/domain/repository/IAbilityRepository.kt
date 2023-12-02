package com.jlahougue.dndcompanion.data_ability.domain.repository

import com.jlahougue.dndcompanion.data_ability.domain.model.Ability

interface IAbilityRepository {
    suspend fun create(characterID: Long): List<Ability>
    suspend fun save(ability: Ability)
    suspend fun saveToLocal(abilities: List<Ability>)
}