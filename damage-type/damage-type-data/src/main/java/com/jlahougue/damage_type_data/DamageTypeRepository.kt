package com.jlahougue.damage_type_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.DND5E_API_URL
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.damage_type_data.util.toDamageType
import com.jlahougue.damage_type_domain.model.DamageType
import com.jlahougue.damage_type_domain.repository.IDamageTypeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class DamageTypeRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val remote: RemoteGenericDataSource,
    private val local: DamageTypeLocalDataSource
) : IDamageTypeRepository {

    private val damageTypesUrl = "$DND5E_API_URL/api/damage-types"

    override suspend fun loadAll(
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val existingDamageTypes = getNames()
        val response = remote.sendGet(damageTypesUrl)

        if (response is Result.Failure) return onApiEvent(ApiEvent.Error(response.error))

        val json = JSONObject(response.getDataOrNull())

        val count = json.getInt("count")
        if (count == existingDamageTypes.size) {
            return onApiEvent(ApiEvent.Finish)
        }
        onApiEvent(ApiEvent.SetMaxProgress(count))

        val results = json.getJSONArray("results")
        (0 until results.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                val name = results.getJSONObject(it).getString("name")
                val url = results.getJSONObject(it).getString("url")
                if (existingDamageTypes.contains(name)) {
                    return@launch onApiEvent(ApiEvent.Skip())
                }
                loadDamageType(url, onApiEvent)
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadDamageType(
        url: String,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val response = remote.sendGet(DND5E_API_URL + url)
        if (response is Result.Failure) return onApiEvent(ApiEvent.Skip())

        val json = JSONObject(response.getDataOrNull())
        save(json.toDamageType())

        onApiEvent(ApiEvent.UpdateProgress)
    }

    override suspend fun save(damageType: DamageType): Boolean {
        return local.insert(damageType) != -1L
    }

    override suspend fun getNames(): List<String> {
        return local.getNames()
    }
}