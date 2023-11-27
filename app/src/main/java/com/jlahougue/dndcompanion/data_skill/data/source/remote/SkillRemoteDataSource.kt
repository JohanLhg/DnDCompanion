package com.jlahougue.dndcompanion.data_skill.data.source.remote

import com.jlahougue.dndcompanion.data_skill.domain.model.Skill

interface SkillRemoteDataSource {
    fun save(skill: Skill)
}