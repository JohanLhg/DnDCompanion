package com.jlahougue.user_info_domain.repository

import com.jlahougue.user_info_domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface IUserInfoRepository {
    suspend fun update(userId: String?, characterId: Long?, unitSystem: com.jlahougue.settings_domain.model.UnitSystem?)
    fun get(): Flow<UserInfo>
}