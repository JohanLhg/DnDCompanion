package com.jlahougue.dndcompanion.data_spell.data.di

import com.jlahougue.dndcompanion.data_spell.data.repository.SpellRepository
import com.jlahougue.dndcompanion.data_spell.data.source.local.SpellLocalDataSource
import com.jlahougue.dndcompanion.data_spell.data.source.remote.SpellRemoteDataSource
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