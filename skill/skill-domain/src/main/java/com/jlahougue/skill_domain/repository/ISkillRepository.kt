package com.jlahougue.skill_domain.repository

import com.jlahougue.skill_domain.model.Skill
import com.jlahougue.skill_domain.model.SkillView
import kotlinx.coroutines.flow.Flow

interface ISkillRepository {
    suspend fun create(characterID:Long): List<Skill>
    suspend fun save(skill: Skill)
    suspend fun saveToLocal(skills: List<Skill>)
    suspend fun clearLocal()
    suspend fun delete(characterID: Long)
    fun get(characterId: Long): Flow<List<SkillView>>
}