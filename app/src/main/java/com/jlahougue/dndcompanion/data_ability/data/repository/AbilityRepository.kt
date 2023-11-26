package com.jlahougue.dndcompanion.data_ability.data.repository

import com.jlahougue.dndcompanion.data_ability.data.source.AbilityFirebaseDao
import com.jlahougue.dndcompanion.data_ability.data.source.AbilityRoomDao
import com.jlahougue.dndcompanion.data_ability.domain.model.Ability
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_ability.domain.repository.IAbilityRepository

class AbilityRepository(
    private val abilityRoomDao: AbilityRoomDao,
    private val abilityFirebaseDao: AbilityFirebaseDao
): IAbilityRepository {
    override suspend fun create(characterID: Long) {
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.STRENGTH))
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.DEXTERITY))
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.CONSTITUTION))
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.INTELLIGENCE))
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.WISDOM))
        abilityRoomDao.insert(Ability(cid = characterID, name = AbilityName.CHARISMA))
    }

    override suspend fun save(ability: Ability) {
        abilityRoomDao.insert(ability)
        abilityFirebaseDao.save(ability)
    }
}