package com.jlahougue.damage_type_data.source.remote

import com.jlahougue.core_data_remote_instance.Dnd5eDataSource
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.damage_type_data.R
import com.jlahougue.damage_type_domain.model.DamageType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class DamageTypeDnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dnd5eDataSource: Dnd5eDataSource
): DamageTypeRemoteDataSource {
    override suspend fun load(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeRemoteListener: DamageTypeRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(Dnd5eDataSource.DAMAGE_TYPES_URL)
        if (response == null) {
            val errorMessage = UiText.StringResource(R.string.error_fetching_damage_types)
            return onApiEvent(ApiEvent.Error(errorMessage))
        }
        val json = JSONObject(response)

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
                loadDamageType(url, onApiEvent, damageTypeRemoteListener)
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadDamageType(
        url: String,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeRemoteListener: DamageTypeRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(url) ?: return onApiEvent(ApiEvent.Skip())

        val json = JSONObject(response)
        val name = json.getString("name")
        val desc = json.getJSONArray("desc").join("\n")

        damageTypeRemoteListener.save(DamageType(name, desc))

        onApiEvent(ApiEvent.UpdateProgress)
    }
}