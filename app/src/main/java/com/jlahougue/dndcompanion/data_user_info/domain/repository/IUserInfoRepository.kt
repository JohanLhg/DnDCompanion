package com.jlahougue.dndcompanion.data_user_info.domain.repository

import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface IUserInfoRepository {
    suspend fun update(userId: String?, characterId: Long?, unitSystem: UnitSystem?)
    fun get(): Flow<UserInfo>
}