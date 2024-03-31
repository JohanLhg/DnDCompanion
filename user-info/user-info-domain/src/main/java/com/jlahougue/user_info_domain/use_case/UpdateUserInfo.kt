package com.jlahougue.user_info_domain.use_case

import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.model.UnitSystem
import com.jlahougue.user_info_domain.repository.IUserInfoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class UpdateUserInfo(
    private val dispatcherProvider: DispatcherProvider,
    private val userInfoRepository: IUserInfoRepository
) {
    operator fun invoke(
        userId: String? = null,
        characterId: Long? = null,
        unitSystem: UnitSystem? = null
    ) {
        CoroutineScope(dispatcherProvider.io).launch {
            userInfoRepository.update(userId, characterId, unitSystem)
        }
    }
}
