package com.jlahougue.dndcompanion.data_property.data.source.remote

import com.jlahougue.dndcompanion.R
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.ApiEvent
import com.jlahougue.dndcompanion.core.data.source.remote.subsources.Dnd5eDataSource
import com.jlahougue.dndcompanion.core.domain.util.UiText
import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_property.domain.model.Property
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class PropertyDnd5eDataSource(
    private val dispatcherProvider: DispatcherProvider,
    private val dnd5eDataSource: Dnd5eDataSource
): PropertyRemoteDataSource {
    override suspend fun getProperties(
        existingPropertyNames: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        propertyRemoteListener: PropertyRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(Dnd5eDataSource.WEAPON_PROPERTIES_URL)
        if (response == null) {
            val errorMessage = UiText.StringResource(R.string.error_api_properties)
            return onApiEvent(ApiEvent.Error(errorMessage))
        }
        val json = JSONObject(response)

        val count = json.getInt("count")
        if (count == existingPropertyNames.size) return onApiEvent(ApiEvent.Finish)
        onApiEvent(ApiEvent.SetMaxProgress(count))

        val results = json.getJSONArray("results")
        (0..<results.length()).map {
            CoroutineScope(dispatcherProvider.io).launch {
                val name = results.getJSONObject(it).getString("name")
                val url = results.getJSONObject(it).getString("url")

                if (existingPropertyNames.contains(name))
                    onApiEvent(ApiEvent.Skip())
                else getProperty(
                    url,
                    onApiEvent,
                    propertyRemoteListener
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun getProperty(
        url: String,
        onApiEvent: (ApiEvent) -> Unit,
        propertyRemoteListener: PropertyRemoteListener
    ) {
        val response = dnd5eDataSource.sendGet(url) ?: return onApiEvent(ApiEvent.Skip())

        val json = JSONObject(response)
        val name = json.getString("name")
        val descArray = json.getJSONArray("desc")
        var desc = ""
        for (i in 0 until descArray.length()) {
            if (i != 0) desc += "\n\n"
            desc += descArray.getString(i)
        }

        propertyRemoteListener.save(Property(name, desc))

        onApiEvent(ApiEvent.UpdateProgress)
    }
}