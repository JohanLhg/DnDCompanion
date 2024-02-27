package com.jlahougue.dndcompanion.data_skill.data.source.remote

import com.jlahougue.core_data_remote_instance.FirebaseDataSource
import com.jlahougue.skill_domain.model.Skill

class SkillFirebaseDataSource(
    private val firebaseDataSource: FirebaseDataSource
): SkillRemoteDataSource {
    override fun save(skill: Skill) {
        firebaseDataSource.updateCharacterSheet(skill.cid, mapOf("skills.${skill.name}" to skill))
    }
}