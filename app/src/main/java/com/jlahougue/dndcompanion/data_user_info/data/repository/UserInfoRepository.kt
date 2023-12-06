package com.jlahougue.dndcompanion.data_user_info.data.repository

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_user_info.data.source.UserInfoLocalDataSource
import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class UserInfoRepository(
    private val dispatcherProvider: DispatcherProvider,
    private val local: UserInfoLocalDataSource
) : IUserInfoRepository {
    override fun updateUserId(userId: String?) {
        CoroutineScope(dispatcherProvider.io).launch {
            local.updateUserId(userId)
        }
    }

    override fun updateCharacterId(characterId: Long) {
        CoroutineScope(dispatcherProvider.io).launch {
            local.updateCharacterId(characterId)
        }
    }

    override fun get(): Flow<UserInfo> = local.get()
}