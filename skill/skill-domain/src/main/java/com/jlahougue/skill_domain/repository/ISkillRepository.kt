package com.jlahougue.skill_domain.repository

import com.jlahougue.skill_domain.model.Skill

interface ISkillRepository {
    suspend fun create(characterID:Long): List<Skill>
    suspend fun save(skill: Skill)
    suspend fun saveToLocal(skills: List<Skill>)
    suspend fun clearLocal()
    suspend fun delete(characterID: Long)
}