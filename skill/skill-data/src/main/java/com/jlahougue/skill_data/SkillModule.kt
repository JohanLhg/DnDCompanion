package com.jlahougue.skill_data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.skill_domain.di.ISkillModule
import com.jlahougue.skill_domain.use_case.GetSkills
import com.jlahougue.skill_domain.use_case.SkillUseCases

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
    override val useCases by lazy {
        SkillUseCases(
            GetSkills(repository)
        )
    }
}