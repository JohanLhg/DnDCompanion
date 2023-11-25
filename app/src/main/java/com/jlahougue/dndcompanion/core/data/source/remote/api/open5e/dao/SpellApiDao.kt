package com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.dao

import android.util.Log
import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.api.listener.SpellApiListener
import com.jlahougue.dndcompanion.core.data.source.remote.api.open5e.Open5eApiRequest
import com.jlahougue.dndcompanion.core.domain.model.Spell
import com.jlahougue.dndcompanion.core.domain.model.SpellClass
import com.jlahougue.dndcompanion.core.domain.model.SpellDamageType
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class SpellApiDao(
    private val dispatcherProvider: DispatcherProvider,
    private val apiRequest: Open5eApiRequest
) {
    suspend fun getSpells(
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellApiListener: SpellApiListener
    ) {
        val responseCheck = apiRequest.sendGet(Open5eApiRequest.SPELLS_CHECK_URL)
        if (responseCheck == null) {
            val errorMessage = UiText.StringResource(R.string.error_api_spells)
            return onApiEvent(ApiEvent.Error(errorMessage))
        }
        val json = JSONObject(responseCheck)

        val count = json.getInt("count")
        if (count == existingSpellIds.size) {
            return onApiEvent(ApiEvent.SkipCall)
        }
        onApiEvent(ApiEvent.SetMaxProgress(count))

        val itemLimit = 100
        var pageCount = count / itemLimit
        if (count % itemLimit != 0) pageCount++

        (1..pageCount).map {
            CoroutineScope(dispatcherProvider.io).launch {
                val response = apiRequest.sendGetPage(Open5eApiRequest.SPELLS_URL, itemLimit, it)
                    ?: return@launch onApiEvent(ApiEvent.Skip(itemLimit))

                fetchPage(
                    JSONObject(response),
                    existingSpellIds,
                    existingDamageTypes,
                    onApiEvent,
                    spellApiListener
                )
            }
        }.joinAll()
    }

    private suspend fun fetchPage(
        page: JSONObject,
        existingSpellIds: List<String>,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellApiListener: SpellApiListener
    ) {
        val results = page.getJSONArray("results")
        Log.d("LoadSpellsFromRemote", "fetchPage: ${results.length()}")
        (0..<results.length()).map {
            CoroutineScope(Dispatchers.IO).launch {
                val spell = results.getJSONObject(it)
                val id = spell.getString("slug")

                if (existingSpellIds.contains(id)) onApiEvent(ApiEvent.Skip())
                else fetchSpell(
                    spell,
                    existingDamageTypes,
                    onApiEvent,
                    spellApiListener
                )
            }
        }.joinAll()
    }

    private suspend fun fetchSpell(
        jsonSpell: JSONObject,
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        spellApiListener: SpellApiListener
    ) {
        val id = jsonSpell.getString("slug")
        val name = jsonSpell.getString("name").replace("/", " - ")
        val level = jsonSpell.getInt("level_int")
        val castingTime = jsonSpell.getString("casting_time")
        val range = jsonSpell.getString("range")
        val duration = jsonSpell.getString("duration")
        val ritual = jsonSpell.getBoolean("can_be_cast_as_ritual")
        val concentration = jsonSpell.getBoolean("requires_concentration")

        val components = jsonSpell.getString("components")
        val materials = jsonSpell.getString("material")

        val desc = jsonSpell.getString("desc")
        val higherLevel = jsonSpell.getString("higher_level")

        val itemInserted = spellApiListener.save(
            Spell(
                id,
                name,
                level,
                castingTime,
                range,
                components,
                materials,
                ritual,
                concentration,
                duration,
                desc,
                higherLevel
            )
        )

        if (!itemInserted) return onApiEvent(ApiEvent.Skip())

        // Classes
        val classes = mutableListOf<SpellClass>()
        val spellClasses = jsonSpell.getString("dnd_class").split(",")
        for (clazz in spellClasses) {
            if (clazz.isBlank()) continue
            classes.add(SpellClass(id, clazz.trim()))
        }
        spellApiListener.saveClasses(classes)

        // Damage Types
        val damageTypes = getDamageType(existingDamageTypes, desc)
        val spellDamageTypes = mutableListOf<SpellDamageType>()
        for (damageType in damageTypes) {
            spellDamageTypes.add(SpellDamageType(id, damageType))
        }
        spellApiListener.saveDamageTypes(spellDamageTypes)

        onApiEvent(ApiEvent.UpdateProgress)
    }

    private fun getDamageType(damageTypes: List<String>, description: String): List<String> {
        val spellDamageTypes = mutableListOf<String>()
        for (damageType in damageTypes) {
            if (description.contains(damageType,  true)) {
                spellDamageTypes.add(damageType)
            }
        }
        return spellDamageTypes
    }
}