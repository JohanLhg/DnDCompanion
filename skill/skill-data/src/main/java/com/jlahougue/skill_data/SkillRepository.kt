package com.jlahougue.skill_data

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.skill_domain.model.SkillName
import com.jlahougue.skill_domain.repository.ISkillRepository

class SkillRepository(
    private val remote: RemoteUserDataSource,
    private val local: SkillLocalDataSource
): ISkillRepository {
    override suspend fun create(characterID: Long): List<Skill> {
        val skills = listOf(
            Skill(characterID, SkillName.ACROBATICS, AbilityName.DEXTERITY),
            Skill(characterID, SkillName.ANIMAL_HANDLING, AbilityName.WISDOM),
            Skill(characterID, SkillName.ARCANA, AbilityName.INTELLIGENCE),
            Skill(characterID, SkillName.ATHLETICS, AbilityName.STRENGTH),
            Skill(characterID, SkillName.DECEPTION, AbilityName.CHARISMA),
            Skill(characterID, SkillName.HISTORY, AbilityName.INTELLIGENCE),
            Skill(characterID, SkillName.INSIGHT, AbilityName.WISDOM),
            Skill(characterID, SkillName.INTIMIDATION, AbilityName.CHARISMA),
            Skill(characterID, SkillName.INVESTIGATION, AbilityName.INTELLIGENCE),
            Skill(characterID, SkillName.MEDICINE, AbilityName.WISDOM),
            Skill(characterID, SkillName.NATURE, AbilityName.INTELLIGENCE),
            Skill(characterID, SkillName.PERCEPTION, AbilityName.WISDOM),
            Skill(characterID, SkillName.PERFORMANCE, AbilityName.CHARISMA),
            Skill(characterID, SkillName.PERSUASION, AbilityName.CHARISMA),
            Skill(characterID, SkillName.RELIGION, AbilityName.INTELLIGENCE),
            Skill(characterID, SkillName.SLEIGHT_OF_HAND, AbilityName.DEXTERITY),
            Skill(characterID, SkillName.STEALTH, AbilityName.DEXTERITY),
            Skill(characterID, SkillName.SURVIVAL, AbilityName.WISDOM)
        )
        local.insert(skills)
        return skills
    }

    override suspend fun save(skill: Skill) {
        local.insert(skill)
        remote.updateDocument(
            remote.characterUrl(skill.cid),
            mapOf("skills.${skill.name}" to skill)
        )
    }

    override suspend fun saveToLocal(skills: List<Skill>) {
        local.insert(skills)
    }

    override suspend fun clearLocal() = local.clear()

    override suspend fun delete(characterID: Long) {
        local.delete(characterID)
    }
}