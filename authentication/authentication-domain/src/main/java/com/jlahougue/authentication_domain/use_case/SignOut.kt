package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.character_sheet_domain.use_case.CharacterSheetUseCases
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.user_info_domain.use_case.UserInfoUseCases
import kotlinx.coroutines.withContext

class SignOut(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: IAuthRepository,
    private val userInfoUseCases: UserInfoUseCases,
    private val characterSheetUseCases: CharacterSheetUseCases
) {
    suspend operator fun invoke() {
        authRepository.signOut()
        withContext(dispatcherProvider.io) {
            userInfoUseCases.updateUserInfo(
                userId = "",
                characterId = -1L
            )
            characterSheetUseCases.clearCharacters()
        }
    }
}