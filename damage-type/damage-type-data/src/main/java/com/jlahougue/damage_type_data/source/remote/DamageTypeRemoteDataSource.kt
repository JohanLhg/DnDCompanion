package com.jlahougue.damage_type_data.source.remote

import com.jlahougue.core_domain.util.ApiEvent

interface DamageTypeRemoteDataSource {
    suspend fun load(
        existingDamageTypes: List<String>,
        onApiEvent: (ApiEvent) -> Unit,
        damageTypeRemoteListener: DamageTypeRemoteListener
    )
}