package com.jlahougue.dndcompanion.data_user_info.domain.use_case

import com.jlahougue.dndcompanion.core.domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.dndcompanion.data_settings.domain.model.UnitSystem
import com.jlahougue.dndcompanion.data_user_info.domain.repository.IUserInfoRepository
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
