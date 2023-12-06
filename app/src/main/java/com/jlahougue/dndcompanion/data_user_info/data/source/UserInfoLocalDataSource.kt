package com.jlahougue.dndcompanion.data_user_info.data.source

import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface UserInfoLocalDataSource {
    suspend fun updateUserId(userId: String?)
    suspend fun updateCharacterId(characterId: Long)
    fun get(): Flow<UserInfo>
}