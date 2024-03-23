package com.jlahougue.class_data.source.remote

import com.jlahougue.ability_domain.model.AbilityName
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.DND5E_API_URL
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.OPEN5E_API_URL
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.extension.getIntIfExists
import com.jlahougue.core_domain.util.response.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ClassApiRemoteDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dataSource: RemoteGenericDataSource
) : ClassRemoteDataSource {

    private val classesUrl = "$OPEN5E_API_URL/classes"
    private fun classLevelsUrl(className: String) =
        "$DND5E_API_URL/api/classes/${className.lowercase()}/levels"

    override suspend fun load(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    ) {
        val response = dataSource.sendGet(classesUrl)

        if (response is Result.Failure) return onApiEvent(ApiEvent.Error(response.error))

        val data = (response as Result.Success).data
        val json = JSONObject(data)
        val count = json.getInt("count")
        if (count == existingClasses.size) {
            return onApiEvent(ApiEvent.Finish)
        }
        onApiEvent(ApiEvent.SetMaxProgress(count))

        val results = json.getJSONArray("results")
        (0 until results.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                val clazz = results.getJSONObject(it)
                val name = clazz.getString("name")

                if (existingClasses.contains(name))
                    onApiEvent(ApiEvent.Skip())
                else loadClass(
                    clazz,
                    onApiEvent,
                    classRemoteListener
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadClass(
        clazz: JSONObject,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    ) {
        val name = clazz.getString("name")
        val hitDie = clazz.getString("hit_dice").removePrefix("1d").toInt()
        val equipment = clazz.getString("equipment")
        val profSavingThrows = clazz.getString("prof_saving_throws")
        val profSkills = clazz.getString("prof_skills")
        val profArmor = clazz.getString("prof_armor")
        val profWeapons = clazz.getString("prof_weapons")
        val profTools = clazz.getString("prof_tools")
        val spellcastingAbility = AbilityName.from(clazz.getString("spellcasting_ability"))

        val itemInserted = classRemoteListener.save(
            Class(
                name,
                hitDie,
                equipment,
                profSavingThrows,
                profSkills,
                profArmor,
                profWeapons,
                profTools,
                spellcastingAbility
            )
        )

        if (!itemInserted) return onApiEvent(ApiEvent.Skip())

        loadLevels(name, classRemoteListener)

        onApiEvent(ApiEvent.UpdateProgress)
    }

    override suspend fun loadLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    ) {
        val response = dataSource.sendGet(classLevelsUrl(className))

        if (response is Result.Failure) return

        val data = (response as Result.Success).data

        val json = JSONArray(data)
        var classLevel: JSONObject
        (0..<json.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                classLevel = json.getJSONObject(it)
                loadLevel(className, classLevel, classRemoteListener)
            }
        }.joinAll()
    }

    private suspend fun loadLevel(
        clazz: String,
        jsonClassLevel: JSONObject,
        classRemoteListener: ClassRemoteListener
    ) {
        val level = jsonClassLevel.getInt("level")
        val abilityScoreBonuses = jsonClassLevel.getInt("ability_score_bonuses")
        val profBonus = jsonClassLevel.getInt("prof_bonus")

        //Spells
        var cantripsKnown = 0
        var spellsKnown = 0
        var classSpellSlots = listOf<ClassSpellSlot>()
        if (jsonClassLevel.has("spellcasting")) {
            val spellcasting = jsonClassLevel.getJSONObject("spellcasting")
            cantripsKnown = spellcasting.getIntIfExists("cantrips_known")
            spellsKnown = spellcasting.getIntIfExists("spells_known")
            classSpellSlots = loadSpellSlots(clazz, level, spellcasting)
        }

        val itemInserted = classRemoteListener.saveLevel(
            ClassLevel(
                clazz,
                level,
                abilityScoreBonuses,
                profBonus,
                cantripsKnown,
                spellsKnown
            )
        )

        if (!itemInserted) return

        classRemoteListener.saveSpellSlots(classSpellSlots)
    }

    private fun loadSpellSlots(
        clazz: String,
        level: Int,
        spellcasting: JSONObject
    ): List<ClassSpellSlot> {
        val classSpellSlots = mutableListOf<ClassSpellSlot>()
        var slotLevel = 1
        while (spellcasting.has("spell_slots_level_$slotLevel")) {
            val spellSlots = spellcasting.getInt("spell_slots_level_$slotLevel")
            if (spellSlots > 0) {
                classSpellSlots.add(ClassSpellSlot(clazz, level, slotLevel, spellSlots))
            }
            slotLevel++
        }
        return classSpellSlots
    }
}