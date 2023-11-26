package com.jlahougue.dndcompanion.data_class.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Dnd5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.core.domain.util.extension.getIntIfExists
import com.jlahougue.dndcompanion.data_class.domain.model.ClassLevel
import com.jlahougue.dndcompanion.data_class.domain.model.ClassSpellSlot
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ClassDnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dnd5eDataSource: Dnd5eDataSource
) {
    suspend fun getClassLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    ) {
        val response = dnd5eDataSource.getClassLevels(className) ?: return
        val json = JSONArray(response)
        var classLevel: JSONObject
        (0..<json.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                classLevel = json.getJSONObject(it)
                fetchClassLevel(className, classLevel, classRemoteListener)
            }
        }.joinAll()
    }

    private suspend fun fetchClassLevel(
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
            classSpellSlots = fetchClassSpellSlots(clazz, level, spellcasting)
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

    private fun fetchClassSpellSlots(
        clazz: String,
        level: Int,
        spellcasting: JSONObject
    ): List<ClassSpellSlot> {
        val classSpellSlots = mutableListOf<ClassSpellSlot>()
        var slotLevel = 1
        while (spellcasting.has("spell_slots_level_$slotLevel")) {
            val spellSlots = spellcasting.getInt("spell_slots_level_$slotLevel")
            classSpellSlots.add(ClassSpellSlot(clazz, level, slotLevel, spellSlots))
            slotLevel++
        }
        return classSpellSlots
    }
}