package com.jlahougue.user_info_data.source

import android.app.Application
import androidx.datastore.dataStore
import com.jlahougue.settings_domain.model.UnitSystem
import com.jlahougue.user_info_data.util.UserInfoSerializer

class UserInfoDataStoreDataSource(
    application: Application
) : UserInfoLocalDataSource {
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

    override fun get() = dataStore.data

    companion object {
        val Application.userInfoDataStore by dataStore(
            fileName = "user_info.json",
            serializer = UserInfoSerializer
        )
    }
}