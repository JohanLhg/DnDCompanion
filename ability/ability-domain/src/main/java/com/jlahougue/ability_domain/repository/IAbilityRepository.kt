package com.jlahougue.ability_domain.repository

import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.model.AbilityView
import kotlinx.coroutines.flow.Flow

interface IAbilityRepository {
    suspend fun create(characterID: Long): List<Ability>
    suspend fun save(ability: Ability)
    suspend fun saveToLocal(abilities: List<Ability>)
    suspend fun clearLocal()
    suspend fun delete(characterID: Long)
    fun get(characterID: Long): Flow<List<AbilityView>>
}