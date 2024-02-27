package com.jlahougue.dndcompanion.data_skill.data.di

import com.jlahougue.dndcompanion.data_skill.data.repository.SkillRepository
import com.jlahougue.dndcompanion.data_skill.data.source.local.SkillLocalDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillRemoteDataSource
import com.jlahougue.skill_domain.di.ISkillModule

class SkillModule(
    private val remoteDataSource: SkillRemoteDataSource,
    private val localDataSource: SkillLocalDataSource
): ISkillModule {
    override val repository by lazy {
        SkillRepository(
            remoteDataSource,
            localDataSource
        )
    }
}