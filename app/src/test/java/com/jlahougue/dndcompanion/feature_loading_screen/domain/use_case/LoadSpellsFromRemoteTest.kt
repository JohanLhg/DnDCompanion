package com.jlahougue.dndcompanion.feature_loading_screen.domain.use_case

import com.jlahougue.dndcompanion.DnDCompanionApp
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.Open5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao.SpellApiDao
import com.jlahougue.dndcompanion.core.domain.model.DamageType
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.TestDispatcherProvider
import com.jlahougue.dndcompanion.feature_loading_screen.data.repository.FakeDamageTypeRepository
import com.jlahougue.dndcompanion.feature_loading_screen.data.repository.FakeSpellRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class LoadSpellsFromRemoteTest {

    private lateinit var dispatcherProvider: TestDispatcherProvider
    private lateinit var spellRepository: FakeSpellRepository
    private lateinit var damageTypeRepository: FakeDamageTypeRepository
    private lateinit var loadSpellsFromRemote: LoadSpellsFromRemote

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        val spellApiDao = SpellApiDao(
            dispatcherProvider,
            Open5eApiRequest(DnDCompanionApp.appModule.client)
        )
        spellRepository = FakeSpellRepository(spellApiDao)
        damageTypeRepository = FakeDamageTypeRepository()
        loadSpellsFromRemote = LoadSpellsFromRemote(
            dispatcherProvider,
            spellRepository,
            damageTypeRepository
        )

        runBlocking {
            damageTypeRepository.save(DamageType("acid"))
            damageTypeRepository.save(DamageType("bludgeoning"))
            damageTypeRepository.save(DamageType("cold"))
            damageTypeRepository.save(DamageType("fire"))
            damageTypeRepository.save(DamageType("force"))
            damageTypeRepository.save(DamageType("lightning"))
            damageTypeRepository.save(DamageType("necrotic"))
            damageTypeRepository.save(DamageType("piercing"))
            damageTypeRepository.save(DamageType("poison"))
            damageTypeRepository.save(DamageType("psychic"))
            damageTypeRepository.save(DamageType("radiant"))
            damageTypeRepository.save(DamageType("slashing"))
            damageTypeRepository.save(DamageType("thunder"))
        }
    }

    @Test
    fun `load spells from remote`() = runTest {
        val stateFlow = loadSpellsFromRemote()
        assert(stateFlow.count { it.finished } == 1)
    }
}