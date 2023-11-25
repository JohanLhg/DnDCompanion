package com.jlahougue.dndcompanion.core.data.source.remote.api.dnd5e

import com.jlahougue.dndcompanion.core.data.source.remote.api.ApiRequest
import okhttp3.OkHttpClient

class DnD5eApiRequest(
    client: OkHttpClient
): ApiRequest(client) {

    fun sendGetClassLevels(clazz: String): String? {
        return sendGet("/api/classes/${clazz.lowercase()}/levels")
    }

    override fun sendGet(url: String): String? {
        return super.sendGet(DND_API_URL + url)
    }

    companion object {
        private const val DND_API_URL = "https://www.dnd5eapi.co"

        const val SPELLS_URL = "/api/spells"
        const val DAMAGE_TYPES_URL = "/api/damage-types"
        const val WEAPON_PROPERTIES_URL = "/api/weapon-properties"
        const val WEAPON_URL = "/api/equipment-categories/weapon"
    }
}