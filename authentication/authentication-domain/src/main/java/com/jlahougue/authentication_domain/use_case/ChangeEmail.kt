package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.R
import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.core_domain.util.UiText
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ChangeEmail(
    private val dispatcherProvider: DispatcherProvider,
    private val authRepository: IAuthRepository,
    private val signOut: SignOut
) {
    operator fun invoke(
        email: String,
        password: String,
        onError: (UiText) -> Unit,
        onComplete: (Boolean) -> Unit
    ) {
        authRepository.changeEmail(
            email,
            password,
            onUserError = {
                disconnectUser()
                onError(UiText.StringResource(R.string.error_user_not_found))
            },
            onReAuthenticationError = {
                onError(UiText.DynamicString(it))
            },
            onComplete = {
                if (it != null) {
                    disconnectUser()
                    onComplete(true)
                } else {
                    onComplete(false)
                }
            }
        )
    }

    private fun disconnectUser() {
        CoroutineScope(dispatcherProvider.io).launch { signOut() }
    }
}