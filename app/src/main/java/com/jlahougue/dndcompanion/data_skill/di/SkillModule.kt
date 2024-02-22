package com.jlahougue.dndcompanion.data_skill.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_skill.data.repository.SkillRepository

class SkillModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ISkillModule {
    override val repository by lazy {
        SkillRepository(
            remoteDataSource.skillDao,
            localDataSource.skillDao()
        )
    }
}