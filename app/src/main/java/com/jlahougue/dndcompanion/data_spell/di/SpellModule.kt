package com.jlahougue.dndcompanion.data_spell.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.repository.SpellRepository

class SpellModule(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ISpellModule {

    override val repository by lazy {
        SpellRepository(
            remoteDataSource.spellDao,
            localDataSource.spellDao()
        )
    }
}