package com.jlahougue.skill_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.skill_domain.di.ISkillModule

class SkillModule(
    remoteDataSource: RemoteUserDataSource,
    localDataSource: SkillLocalDataSource
): ISkillModule {
    override val repository by lazy {
        SkillRepository(
            remoteDataSource,
            localDataSource
        )
    }
}