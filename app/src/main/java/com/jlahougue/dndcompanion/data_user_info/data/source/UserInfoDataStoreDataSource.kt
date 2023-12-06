package com.jlahougue.dndcompanion.data_user_info.data.source

import android.app.Application
import androidx.datastore.dataStore
import com.jlahougue.dndcompanion.data_user_info.domain.util.UserInfoSerializer

class UserInfoDataStoreDataSource(
    application: Application
) : UserInfoLocalDataSource {
    private val dataStore = application.userInfoDataStore

    override suspend fun updateUserId(userId: String?) {
        dataStore.updateData {
            it.copy(
                userId = userId
            )
        }
    }

    override suspend fun updateCharacterId(characterId: Long) {
        dataStore.updateData {
            it.copy(
                characterId = characterId
            )
        }
    }

    override fun get() = dataStore.data

    companion object {
        val Application.userInfoDataStore by dataStore(
            fileName = "user_info.json",
            serializer = UserInfoSerializer
        )
    }
}