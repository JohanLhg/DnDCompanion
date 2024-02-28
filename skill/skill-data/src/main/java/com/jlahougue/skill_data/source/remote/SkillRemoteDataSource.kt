package com.jlahougue.skill_data.source.remote

import com.jlahougue.skill_domain.model.Skill

interface SkillRemoteDataSource {
    fun save(skill: Skill)
}