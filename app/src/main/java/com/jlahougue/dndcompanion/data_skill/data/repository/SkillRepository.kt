package com.jlahougue.dndcompanion.data_skill.data.repository

import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_skill.data.source.local.SkillLocalDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillRemoteDataSource
import com.jlahougue.dndcompanion.data_skill.domain.model.Skill
import com.jlahougue.dndcompanion.data_skill.domain.model.SkillName
import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository

class SkillRepository(
    private val remoteDataSource: SkillRemoteDataSource,
    private val localDataSource: SkillLocalDataSource
): ISkillRepository {
    override suspend fun create(characterID: Long) {
        localDataSource.insert(Skill(characterID, SkillName.ACROBATICS, AbilityName.DEXTERITY))
        localDataSource.insert(Skill(characterID, SkillName.ANIMAL_HANDLING, AbilityName.WISDOM))
        localDataSource.insert(Skill(characterID, SkillName.ARCANA, AbilityName.INTELLIGENCE))
        localDataSource.insert(Skill(characterID, SkillName.ATHLETICS, AbilityName.STRENGTH))
        localDataSource.insert(Skill(characterID, SkillName.DECEPTION, AbilityName.CHARISMA))
        localDataSource.insert(Skill(characterID, SkillName.HISTORY, AbilityName.INTELLIGENCE))
        localDataSource.insert(Skill(characterID, SkillName.INSIGHT, AbilityName.WISDOM))
        localDataSource.insert(Skill(characterID, SkillName.INTIMIDATION, AbilityName.CHARISMA))
        localDataSource.insert(Skill(characterID, SkillName.INVESTIGATION, AbilityName.INTELLIGENCE))
        localDataSource.insert(Skill(characterID, SkillName.MEDICINE, AbilityName.WISDOM))
        localDataSource.insert(Skill(characterID, SkillName.NATURE, AbilityName.INTELLIGENCE))
        localDataSource.insert(Skill(characterID, SkillName.PERCEPTION, AbilityName.WISDOM))
        localDataSource.insert(Skill(characterID, SkillName.PERFORMANCE, AbilityName.CHARISMA))
        localDataSource.insert(Skill(characterID, SkillName.PERSUASION, AbilityName.CHARISMA))
        localDataSource.insert(Skill(characterID, SkillName.RELIGION, AbilityName.INTELLIGENCE))
        localDataSource.insert(Skill(characterID, SkillName.SLEIGHT_OF_HAND, AbilityName.DEXTERITY))
        localDataSource.insert(Skill(characterID, SkillName.STEALTH, AbilityName.DEXTERITY))
        localDataSource.insert(Skill(characterID, SkillName.SURVIVAL, AbilityName.WISDOM))
    }

    override suspend fun save(skill: Skill) {
        localDataSource.insert(skill)
        remoteDataSource.save(skill)
    }

    override suspend fun saveToLocal(skill: Skill) {
        localDataSource.insert(skill)
    }
}