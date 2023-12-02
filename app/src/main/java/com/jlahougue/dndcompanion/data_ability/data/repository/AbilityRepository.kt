package com.jlahougue.dndcompanion.data_ability.data.repository

import com.jlahougue.dndcompanion.data_ability.data.source.local.AbilityLocalDataSource
import com.jlahougue.dndcompanion.data_ability.data.source.remote.AbilityRemoteDataSource
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository

class AbilityRepository(
    private val remoteDataSource: AbilityRemoteDataSource,
    private val localDataSource: AbilityLocalDataSource
): IAbilityRepository {
    override suspend fun create(characterID: Long): List<Ability> {
        val abilities = listOf(
            Ability(cid = characterID, name = AbilityName.STRENGTH),
            Ability(cid = characterID, name = AbilityName.DEXTERITY),
            Ability(cid = characterID, name = AbilityName.CONSTITUTION),
            Ability(cid = characterID, name = AbilityName.INTELLIGENCE),
            Ability(cid = characterID, name = AbilityName.WISDOM),
            Ability(cid = characterID, name = AbilityName.CHARISMA)
        )
        localDataSource.insert(abilities)
        return abilities
    }

    override suspend fun save(ability: Ability) {
        localDataSource.insert(ability)
        remoteDataSource.save(ability)
    }

    override suspend fun saveToLocal(abilities: List<Ability>) {
        localDataSource.insert(abilities)
    }
}