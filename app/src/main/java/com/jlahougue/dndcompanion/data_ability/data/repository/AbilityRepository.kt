package com.jlahougue.dndcompanion.data_ability.data.repository

import com.jlahougue.dndcompanion.data_ability.data.source.local.AbilityLocalDataSource
import com.jlahougue.dndcompanion.data_ability.data.source.remote.AbilityRemoteDataSource
import kotlinx.coroutines.flow.Flow

class AbilityRepository(
    private val remoteDataSource: AbilityRemoteDataSource,
    private val localDataSource: AbilityLocalDataSource
): com.jlahougue.ability_domain.repository.IAbilityRepository {
    override suspend fun create(characterID: Long): List<com.jlahougue.ability_domain.model.Ability> {
        val abilities = listOf(
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.STRENGTH
            ),
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.DEXTERITY
            ),
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.CONSTITUTION
            ),
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.INTELLIGENCE
            ),
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.WISDOM
            ),
            com.jlahougue.ability_domain.model.Ability(
                cid = characterID,
                name = com.jlahougue.ability_domain.model.AbilityName.CHARISMA
            )
        )
        localDataSource.insert(abilities)
        return abilities
    }

    override suspend fun save(ability: com.jlahougue.ability_domain.model.Ability) {
        localDataSource.insert(ability)
        remoteDataSource.save(ability)
    }

    override suspend fun saveToLocal(abilities: List<com.jlahougue.ability_domain.model.Ability>) {
        localDataSource.insert(abilities)
    }

    override suspend fun delete(characterID: Long) {
        localDataSource.deleteForCharacter(characterID)
    }

    override fun get(characterID: Long): Flow<List<com.jlahougue.ability_domain.model.AbilityView>> {
        return localDataSource.get(characterID)
    }
}