package com.jlahougue.dndcompanion.data_user_info.data.repository

import com.jlahougue.dndcompanion.data_user_info.data.source.UserInfoLocalDataSource
import com.jlahougue.user_info_domain.model.UserInfo
import com.jlahougue.user_info_domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.Flow

class UserInfoRepository(private val local: UserInfoLocalDataSource) : IUserInfoRepository {
    override suspend fun update(userId: String?, characterId: Long?, unitSystem: com.jlahougue.settings_domain.model.UnitSystem?) {
        local.update(userId, characterId, unitSystem)
    }

    override fun get(): Flow<UserInfo> = local.get()
}