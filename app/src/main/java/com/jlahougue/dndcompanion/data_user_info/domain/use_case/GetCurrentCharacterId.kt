package com.jlahougue.dndcompanion.data_user_info.domain.use_case

import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetCurrentCharacterId(
    private val userInfoRepository: IUserInfoRepository
) {

    operator fun invoke(): Flow<Long> {
        return userInfoRepository.get().map { userInfo ->
            userInfo.characterId
        }
    }
}