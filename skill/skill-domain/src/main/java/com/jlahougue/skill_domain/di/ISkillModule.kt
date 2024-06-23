package com.jlahougue.skill_domain.di

import com.jlahougue.skill_domain.repository.ISkillRepository
import com.jlahougue.skill_domain.use_case.SkillUseCases

interface ISkillModule {
    val repository: ISkillRepository
    val useCases: SkillUseCases
}