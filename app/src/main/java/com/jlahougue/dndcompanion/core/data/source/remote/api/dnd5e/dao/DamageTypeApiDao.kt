package com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e.dao

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e.DnD5eApiRequest
import com.jlahougue.dndcompanion.core.data.source.remote.api.event.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.api.listener.DamageTypeApiListener
import com.jlahougue.dndcompanion.core.domain.model.DamageType
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class DamageTypeApiDao(
    private val dispatcherProvider: DispatcherProvider,
    private val apiRequest: DnD5eApiRequest
) {
    suspend fun getDamageTypes(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeApiListener: DamageTypeApiListener
    ) {
        val response = apiRequest.sendGet(DnD5eApiRequest.DAMAGE_TYPES_URL)
        if (response == null) {
            val errorMessage = UiText.StringResource(R.string.error_api_spells)
            return onApiEvent(ApiEvent.Error(errorMessage))
        }
        val json = JSONObject(response)

        val count = json.getInt("count")
        if (count == existingDamageTypes.size) {
            return onApiEvent(ApiEvent.SkipCall)
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
                fetchDamageType(url, onApiEvent, damageTypeApiListener)
            }
        }.joinAll()
    }

    private suspend fun fetchDamageType(
        url: String,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeApiListener: DamageTypeApiListener
    ) {
        val response = apiRequest.sendGet(url) ?: return onApiEvent(ApiEvent.Skip())

        val json = JSONObject(response)
        val name = json.getString("name")
        val desc = json.getJSONArray("desc").join("\n")

        damageTypeApiListener.save(DamageType(name, desc))

        onApiEvent(ApiEvent.UpdateProgress)
    }
}