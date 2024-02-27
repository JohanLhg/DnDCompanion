package com.jlahougue.user_info_data.source

import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.user_info_domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoLocalDataSource {
    suspend fun update(userId: String?, characterId: Long?, unitSystem: UnitSystem?)
    fun get(): Flow<UserInfo>
}