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
    override suspend fun create(characterID: Long) {
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.STRENGTH))
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.DEXTERITY))
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.CONSTITUTION))
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.INTELLIGENCE))
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.WISDOM))
        localDataSource.insert(Ability(cid = characterID, name = AbilityName.CHARISMA))
    }

    override suspend fun save(ability: Ability) {
        localDataSource.insert(ability)
        remoteDataSource.save(ability)
    }

    override suspend fun saveToLocal(abilities: List<Ability>) {
        localDataSource.insert(abilities)
    }
}