package com.jlahougue.spell_data.di

import com.jlahougue.spell_data.repository.SpellRepository
import com.jlahougue.spell_data.source.local.SpellLocalDataSource
import com.jlahougue.spell_data.source.remote.SpellRemoteDataSource
import com.jlahougue.spell_domain.di.ISpellModule

class SpellModule(
    private val remoteDataSource: SpellRemoteDataSource,
    private val localDataSource: SpellLocalDataSource
): ISpellModule {

    override val repository by lazy {
        SpellRepository(
            remoteDataSource,
            localDataSource
        )
    }
}