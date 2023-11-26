package com.jlahougue.dndcompanion.data_damage_type.data.source.remote

import com.jlahougue.dndcompanion.data_damage_type.domain.model.DamageType

interface DamageTypeRemoteListener {
    suspend fun save(damageType: DamageType): Boolean
}