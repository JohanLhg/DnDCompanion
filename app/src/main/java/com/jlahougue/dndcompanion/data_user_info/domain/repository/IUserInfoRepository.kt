package com.jlahougue.dndcompanion.data_user_info.domain.repository

import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow

interface IUserInfoRepository {
    fun updateUserId(userId: String?)
    fun updateCharacterId(characterId: Long)
    fun get(): Flow<UserInfo>
}