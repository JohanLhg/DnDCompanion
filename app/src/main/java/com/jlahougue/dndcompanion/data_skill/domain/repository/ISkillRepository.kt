package com.jlahougue.dndcompanion.data_skill.domain.repository

import com.jlahougue.dndcompanion.data_skill.domain.model.Skill

interface ISkillRepository {
    suspend fun create(characterID:Long)
    suspend fun save(skill: Skill)
    suspend fun saveToLocal(skill: Skill)
}