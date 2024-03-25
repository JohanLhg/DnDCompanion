package com.jlahougue.property_data

import com.jlahougue.core_data_interface.RemoteGenericDataSource
import com.jlahougue.core_data_interface.RemoteGenericDataSource.Companion.DND5E_API_URL
import com.jlahougue.core_domain.util.ApiEvent
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.response.Result
import com.jlahougue.property_data.util.toWeaponProperty
import com.jlahougue.property_domain.model.Property
import com.jlahougue.property_domain.repository.IPropertyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import org.json.JSONObject

class PropertyRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val remote: RemoteGenericDataSource,
    private val local: PropertyLocalDataSource
): IPropertyRepository {

    private val propertyUrl = "$DND5E_API_URL/api/weapon-properties"

    override suspend fun loadAll(onApiEvent: (ApiEvent) -> Unit) {
        val existingPropertyNames = getNames()
        val response = remote.sendGet(propertyUrl)
        if (response is Result.Failure) return onApiEvent(ApiEvent.Error(response.error))

        val json = JSONObject(response.getDataOrNull())

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
                else loadProperty(
                    url,
                    onApiEvent
                )
            }
        }.joinAll()

        onApiEvent(ApiEvent.Finish)
    }

    private suspend fun loadProperty(
        url: String,
        onApiEvent: (ApiEvent) -> Unit
    ) {
        val response = remote.sendGet(DND5E_API_URL + url)
        if (response is Result.Failure) return onApiEvent(ApiEvent.Skip())

        val json = JSONObject(response.getDataOrNull())
        save(json.toWeaponProperty())

        onApiEvent(ApiEvent.UpdateProgress)
    }

    override suspend fun save(property: Property): Boolean {
        return local.insert(property) != -1L
    }

    override suspend fun getNames(): List<String> {
        return local.getNames()
    }
}