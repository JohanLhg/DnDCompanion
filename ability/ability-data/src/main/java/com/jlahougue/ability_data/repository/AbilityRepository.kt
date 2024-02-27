package com.jlahougue.ability_data.repository

import com.jlahougue.ability_data.source.local.AbilityLocalDataSource
import com.jlahougue.ability_data.source.remote.AbilityRemoteDataSource
import com.jlahougue.ability_domain.model.Ability
import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.ability_domain.model.AbilityView
import com.jlahougue.ability_domain.repository.IAbilityRepository
import kotlinx.coroutines.flow.Flow

class AbilityRepository(
    private val remoteDataSource: AbilityRemoteDataSource,
    private val localDataSource: AbilityLocalDataSource
): IAbilityRepository {
    override suspend fun create(characterID: Long): List<Ability> {
        val abilities = listOf(
            Ability(
                cid = characterID,
                name = AbilityName.STRENGTH
            ),
            Ability(
                cid = characterID,
                name = AbilityName.DEXTERITY
            ),
            Ability(
                cid = characterID,
                name = AbilityName.CONSTITUTION
            ),
            Ability(
                cid = characterID,
                name = AbilityName.INTELLIGENCE
            ),
            Ability(
                cid = characterID,
                name = AbilityName.WISDOM
            ),
            Ability(
                cid = characterID,
                name = AbilityName.CHARISMA
            )
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

    override suspend fun delete(characterID: Long) {
        localDataSource.deleteForCharacter(characterID)
    }

    override fun get(characterID: Long): Flow<List<AbilityView>> {
        return localDataSource.get(characterID)
    }
}