package com.jlahougue.dndcompanion.core.data.source.remote.api.listener

import com.jlahougue.dndcompanion.core.domain.model.DamageType

interface DamageTypeApiListener {
    suspend fun save(damageType: DamageType): Boolean
}