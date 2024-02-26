package com.jlahougue.dndcompanion.data_user_info.data.source

import com.jlahougue.user_info_domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoLocalDataSource {
    suspend fun update(userId: String?, characterId: Long?, unitSystem: com.jlahougue.settings_domain.model.UnitSystem?)
    fun get(): Flow<UserInfo>
}