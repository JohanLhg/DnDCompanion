package com.jlahougue.damage_type_data.source.remote

import com.jlahougue.damage_type_domain.model.DamageType

interface DamageTypeRemoteListener {
    suspend fun save(damageType: DamageType): Boolean
}