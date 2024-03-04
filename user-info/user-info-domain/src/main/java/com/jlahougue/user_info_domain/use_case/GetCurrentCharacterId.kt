package com.jlahougue.user_info_domain.use_case

import com.jlahougue.user_info_domain.repository.IUserInfoRepository
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