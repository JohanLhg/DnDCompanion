package com.jlahougue.class_data

import com.jlahougue.class_data.util.toClass
import com.jlahougue.class_data.util.toClassLevel
import com.jlahougue.class_data.util.toClassSpellSlots
import com.jlahougue.class_domain.model.Class
import com.jlahougue.class_domain.model.ClassLevel
import com.jlahougue.class_domain.model.ClassSpellSlot
import com.jlahougue.class_domain.repository.IClassRepository
import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.DND5E_API_URL
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.OPEN5E_API_URL
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.response.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ClassRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val remote: RemoteGenericDataSource,
    private val local: ClassLocalDataSource
): IClassRepository {

    private val classesUrl = "$OPEN5E_API_URL/classes"
    private fun classLevelsUrl(className: String) =
        "$DND5E_API_URL/api/classes/${className.lowercase()}/levels"

    override suspend fun save(clazz: Class): Boolean {
        return local.insert(clazz) != -1L
    }

    override suspend fun saveLevel(classLevel: ClassLevel): Boolean {
        return local.insertLevel(classLevel) != -1L
    }

    override suspend fun saveSpellSlots(classSpellSlots: List<ClassSpellSlot>) {
        local.insertSpellSlots(classSpellSlots)
    }

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingClasses = local.getNames()

        val response = remote.sendGet(classesUrl)

        if (response is Result.Failure) return onApiEvent(ApiEvent.Error(response.error))

        val json = JSONObject(response.getDataOrNull())
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
                else {
                    if (!save(clazz.toClass())) return@launch onApiEvent(ApiEvent.Skip())
                    loadLevels(name)
                    onApiEvent(ApiEvent.UpdateProgress)
                }
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadLevels(className: String) {
        val response = remote.sendGet(classLevelsUrl(className))

        if (response is Result.Failure) return

        val json = JSONArray(response.getDataOrNull())
        var classLevel: JSONObject
        (0..<json.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                classLevel = json.getJSONObject(it)

                if (!saveLevel(classLevel.toClassLevel(className))) return@launch
                saveSpellSlots(classLevel.toClassSpellSlots(className))
            }
        }.joinAll()
    }

    override suspend fun get(name: String) = local.get(name)

    override suspend fun get() = local.get()

    override suspend fun getLevels(name: String) = local.getLevels(name)

    override suspend fun getSpellcasterClasses() = local.getSpellcasterClasses()
}