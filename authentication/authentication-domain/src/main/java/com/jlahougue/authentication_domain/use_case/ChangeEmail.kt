package com.jlahougue.authentication_domain.use_case

import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.core_domain.util.dispatcherProvider.DispatcherProvider
import com.jlahougue.core_domain.util.extension.isValidEmail
import com.jlahougue.core_domain.util.response.Result
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
        onComplete: (Result<String, EmailChangeError>) -> Unit
    ) {
        if (email.isBlank()) {
            return onComplete(Result.Failure(EmailChangeError.EMAIL_EMPTY))
        }
        if (!email.isValidEmail()) {
            return onComplete(Result.Failure(EmailChangeError.EMAIL_INVALID))
        }
        authRepository.changeEmail(email, password) {
            if (it is Result.Failure && it.error == EmailChangeError.USER_NOT_FOUND) {
                CoroutineScope(dispatcherProvider.io).launch { signOut() }
            }
            onComplete(it)
        }
    }
}