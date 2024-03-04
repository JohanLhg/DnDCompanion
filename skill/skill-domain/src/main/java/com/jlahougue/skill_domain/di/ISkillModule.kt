package com.jlahougue.skill_domain.di

import com.jlahougue.skill_domain.repository.ISkillRepository

interface ISkillModule {
    val repository: ISkillRepository
}