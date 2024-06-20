package com.jlahougue.skill_domain.use_case

import com.jlahougue.skill_domain.repository.ISkillRepository

class GetSkills(private val repository: ISkillRepository) {
    operator fun invoke(characterId: Long) = repository.get(characterId)
}