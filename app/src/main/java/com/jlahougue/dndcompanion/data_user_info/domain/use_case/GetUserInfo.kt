package com.jlahougue.dndcompanion.data_user_info.domain.use_case

import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository

class GetUserInfo(private val userInfoRepository: IUserInfoRepository) {

    operator fun invoke() = userInfoRepository.get()
}