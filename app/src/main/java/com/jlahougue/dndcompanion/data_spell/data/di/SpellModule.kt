package com.jlahougue.dndcompanion.data_spell.data.di

import com.jlahougue.dndcompanion.core.data.source.local.LocalDataSource
import com.jlahougue.dndcompanion.core.data.source.remote.RemoteDataSource
import com.jlahougue.dndcompanion.data_spell.data.repository.SpellRepository
import com.jlahougue.spell_domain.di.ISpellModule

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