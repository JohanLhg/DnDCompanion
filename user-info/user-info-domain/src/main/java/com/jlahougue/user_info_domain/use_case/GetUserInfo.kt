package com.jlahougue.user_info_domain.use_case

import com.jlahougue.user_info_domain.repository.IUserInfoRepository

class GetUserInfo(private val userInfoRepository: IUserInfoRepository) {

    operator fun invoke() = userInfoRepository.get()
}