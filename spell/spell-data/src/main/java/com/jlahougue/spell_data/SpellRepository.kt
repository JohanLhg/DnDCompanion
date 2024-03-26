package com.jlahougue.spell_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.OPEN5E_API_URL
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.spell_data.util.toSpell
import com.jlahougue.spell_domain.model.Spell
import com.jlahougue.spell_domain.model.SpellClass
import com.jlahougue.spell_domain.model.SpellDamageType
import com.jlahougue.spell_domain.repository.ISpellRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class SpellRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val remote: RemoteGenericDataSource,
    private val local: SpellLocalDataSource
): ISpellRepository {

    private val spellsUrl = "$OPEN5E_API_URL/spells/?limit=1"
    private val pageLimit = 100
    private fun spellsPageUrl(page: Int) = "$OPEN5E_API_URL/spells/?limit=$pageLimit&page=$page"

    override suspend fun loadAll(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingSpellIds = getIds()
        val responseCheck = remote.sendGet(spellsUrl)
        if (responseCheck is Result.Failure) return onApiEvent(ApiEvent.Error(responseCheck.error))

        val json = JSONObject(responseCheck.getDataOrNull())

        val count = json.getInt("count")
        if (count == existingSpellIds.size) return onApiEvent(ApiEvent.Finish)
        onApiEvent(ApiEvent.SetMaxProgress(count))

        var pageCount = count / pageLimit
        if (count % pageLimit != 0) pageCount++

        (1..pageCount).map { pageNumber ->
            CoroutineScope(dispatcherProvider.io).launch {
                loadPage(
                    pageNumber,
                    existingSpellIds,
                    existingDamageTypes,
                    onApiEvent
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadPage(
        pageNumber: Int,
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val response = remote.sendGet(spellsPageUrl(pageNumber))
        if (response is Result.Failure) return onApiEvent(ApiEvent.Skip(pageLimit))

        val page = JSONObject(response.getDataOrNull())
        val results = page.getJSONArray("results")
        (0..<results.length()).map {
            CoroutineScope(Dispatchers.IO).launch {
                val spell = results.getJSONObject(it)
                val id = spell.getString("slug")

                if (existingSpellIds.contains(id)) onApiEvent(ApiEvent.Skip())
                else loadSpell(
                    spell,
                    existingDamageTypes,
                    onApiEvent
                )
            }
        }.joinAll()
    }

    private suspend fun loadSpell(
        json: JSONObject,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val spell = json.toSpell()
        if (!save(spell)) return onApiEvent(ApiEvent.Skip())

        // Classes
        val classes = mutableListOf<SpellClass>()
        val spellClasses = json.getString("dnd_class").split(",")
        for (clazz in spellClasses) {
            if (clazz.isBlank()) continue
            classes.add(SpellClass(spell.id, clazz.trim()))
        }
        saveClasses(classes)

        // Damage Types
        val damageTypes = mutableListOf<SpellDamageType>()
        for (damageType in existingDamageTypes) {
            if (spell.description.contains(damageType,  true)) {
                damageTypes.add(SpellDamageType(spell.id, damageType))
            }
        }
        saveDamageTypes(damageTypes)

        onApiEvent(ApiEvent.UpdateProgress)
    }

    override suspend fun save(spell: Spell): Boolean {
        return local.insert(spell) != -1L
    }

    override suspend fun saveClasses(spellClasses: List<SpellClass>) {
        local.insertClasses(spellClasses)
    }

    override suspend fun saveDamageTypes(spellDamageTypes: List<SpellDamageType>) {
        local.insertDamageTypes(spellDamageTypes)
    }

    override suspend fun getIds(): List<String> {
        return local.getIds()
    }
}