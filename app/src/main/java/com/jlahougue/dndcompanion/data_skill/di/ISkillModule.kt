package com.jlahougue.dndcompanion.data_skill.di

import com.jlahougue.dndcompanion.data_skill.domain.repository.ISkillRepository

interface ISkillModule {
    val skillRepository: ISkillRepository
}