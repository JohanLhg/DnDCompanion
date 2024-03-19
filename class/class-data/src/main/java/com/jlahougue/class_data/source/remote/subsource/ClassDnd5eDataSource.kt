package com.jlahougue.class_data.source.remote.subsource

import com.jlahougue.class_data.source.remote.ClassRemoteListener
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.extension.getIntIfExists
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ClassDnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dnd5eDataSource: Dnd5eDataSource
) {
    suspend fun loadLevels(
        className: String,
        classRemoteListener: ClassRemoteListener
    ) {
        val response = dnd5eDataSource.getClassLevels(className) ?: return
        val json = JSONArray(response)
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