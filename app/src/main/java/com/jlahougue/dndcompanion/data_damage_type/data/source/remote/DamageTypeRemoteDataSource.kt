package com.jlahougue.dndcompanion.data_damage_type.data.source.remote

import com.jlahougue.dndcompanion.core.data.source.remote.subsource.ApiEvent

interface DamageTypeRemoteDataSource {
    suspend fun load(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeRemoteListener: DamageTypeRemoteListener
    )
}