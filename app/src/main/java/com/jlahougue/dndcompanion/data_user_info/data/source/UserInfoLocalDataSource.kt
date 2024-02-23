package com.jlahougue.dndcompanion.data_user_info.data.source

import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoLocalDataSource {
    suspend fun update(userId: String?, characterId: Long?, unitSystem: UnitSystem?)
    fun get(): Flow<UserInfo>
}