package com.jlahougue.dndcompanion.data_skill.data.source.remote

import com.jlahougue.skill_domain.model.Skill

interface SkillRemoteDataSource {
    fun save(skill: Skill)
}