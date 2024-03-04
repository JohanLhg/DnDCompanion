package com.jlahougue.skill_data.di

import com.jlahougue.skill_data.repository.SkillRepository
import com.jlahougue.skill_data.source.local.SkillLocalDataSource
import com.jlahougue.skill_data.source.remote.SkillRemoteDataSource
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