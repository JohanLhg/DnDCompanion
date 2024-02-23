package com.jlahougue.dndcompanion.data_user_info.data.repository

import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_user_info.data.source.UserInfoLocalDataSource
import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.Flow

class UserInfoRepository(private val local: UserInfoLocalDataSource) : IUserInfoRepository {
    override suspend fun update(userId: String?, characterId: Long?, unitSystem: UnitSystem?) {
        local.update(userId, characterId, unitSystem)
    }

    override fun get(): Flow<UserInfo> = local.get()
}