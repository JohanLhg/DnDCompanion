package com.jlahougue.dndcompanion.di

import android.app.Application
import androidx.room.Room
import com.jlahougue.dndcompanion.core.data.repository.ClassRepository
import com.jlahougue.dndcompanion.core.data.repository.DamageTypeRepository
import com.jlahougue.dndcompanion.core.data.repository.PropertyRepository
import com.jlahougue.dndcompanion.core.data.repository.SpellRepository
import com.jlahougue.dndcompanion.core.data.repository.WeaponRepository
import com.jlahougue.dndcompanion.core.data.source.local.DnDCompanionDatabase
import com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e.DnD5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e.dao.DamageTypeApiDao
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.Open5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import okhttp3.OkHttpClient

class RepositoriesModule(
    private val app: Application,
    private val dispatcherProvider: DispatcherProvider
) : IRepositoriesModule {

    private val database by lazy {
        Room.databaseBuilder(
            app,
            DnDCompanionDatabase::class.java,
            DnDCompanionDatabase.DATABASE_NAME
        ).build()
    }
    private val httpClient by lazy {
        OkHttpClient()
    }
    private val open5eApiRequest by lazy {
        Open5eApiRequest(httpClient)
    }
    private val dnD5eApiRequest by lazy {
        DnD5eApiRequest(httpClient)
    }

    override val classRepository by lazy {
        ClassRepository(
            database.classDao(),
            database.classLevelDao(),
            database.classSpellSlotDao()
        )
    }
    override val damageTypeRepository by lazy {
        val damageTypeApiDao = DamageTypeApiDao(
            dispatcherProvider,
            dnD5eApiRequest
        )
        DamageTypeRepository(
            damageTypeApiDao,
            database.damageTypeDao()
        )
    }
    override val propertyRepository by lazy {
        PropertyRepository(database.propertyDao())
    }
    override val spellRepository by lazy {
        val spellApiDao = SpellApiDao(
            dispatcherProvider,
            open5eApiRequest
        )
        SpellRepository(
            spellApiDao,
            database.spellDao(),
            database.spellClassDao(),
            database.spellDamageTypeDao()
        )
    }
    override val weaponRepository by lazy {
        WeaponRepository(database.weaponDao(), database.weaponPropertyDao())
    }
}