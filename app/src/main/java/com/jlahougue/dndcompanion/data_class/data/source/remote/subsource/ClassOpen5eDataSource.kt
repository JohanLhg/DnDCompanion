package com.jlahougue.dndcompanion.data_class.data.source.remote.subsource

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.subsource.Open5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_ability.domain.model.AbilityName
import com.jlahougue.dndcompanion.data_class.data.source.remote.ClassRemoteListener
import com.jlahougue.dndcompanion.data_class.domain.model.Class
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class ClassOpen5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val open5eDataSource: Open5eDataSource
) {
    suspend fun load(
        existingClasses: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        classRemoteListener: ClassRemoteListener
    ) {
        val response = open5eDataSource.sendGet(Open5eDataSource.CLASSES_URL)
        if (response == null) {
            val errorMessage = UiText.StringResource(R.string.error_api_classes)
            return onApiEvent(ApiEvent.Error(errorMessage))
        }

        val json = JSONObject(response)
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

        classRemoteListener.loadClassLevels(name)

        onApiEvent(ApiEvent.UpdateProgress)
    }
}