package com.jlahougue.user_info_data

import android.app.Application
import androidx.datastore.dataStore
import com.jlahougue.user_info_data.util.UserInfoSerializer
import com.jlahougue.user_info_domain.model.UnitSystem
import com.jlahougue.user_info_domain.model.UserInfo
import com.jlahougue.user_info_domain.repository.IUserInfoRepository
import kotlinx.coroutines.flow.Flow

class UserInfoRepository(application: Application) : IUserInfoRepository {

    private val dataStore = application.userInfoDataStore

    override suspend fun update(
        userId: String?,
        characterId: Long?,
        unitSystem: UnitSystem?
    ) {
        dataStore.updateData {
            it.copy(
                userId = userId?: it.userId,
                characterId = characterId?: it.characterId,
                unitSystem = unitSystem?: it.unitSystem
            )
        }
    }

    override fun get(): Flow<UserInfo> = dataStore.data

    companion object {
        val Application.userInfoDataStore by dataStore(
            fileName = "user_info.json",
            serializer = UserInfoSerializer
        )
    }
}