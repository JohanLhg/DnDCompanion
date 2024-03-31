package com.jlahougue.authentication_data

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.jlahougue.authentication_data.util.asAuthenticationError
import com.jlahougue.authentication_data.util.asEmailChangeError
import com.jlahougue.authentication_data.util.asPasswordChangeError
import com.jlahougue.authentication_domain.repository.IAuthRepository
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.authentication_domain.util.PasswordChangeError
import com.jlahougue.core_domain.util.response.Result

class AuthRepository(private val remote: FirebaseAuth) : IAuthRepository {

    override fun getUserId() = remote.currentUser?.uid

    override suspend fun register(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        remote.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(Result.Success(it.user!!.uid))
            }
            .addOnFailureListener {
                val error = when (it) {
                    is FirebaseAuthException -> it.asAuthenticationError()
                    else -> AuthenticationError.UNKNOWN
                }
                onComplete(Result.Failure(error))
            }
    }

    override suspend fun login(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        remote.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(Result.Success(it.user!!.uid))
            }
            .addOnFailureListener {
                val error = when (it) {
                    is FirebaseAuthException -> it.asAuthenticationError()
                    else -> AuthenticationError.UNKNOWN
                }
                onComplete(Result.Failure(error))
            }
    }

    override fun changeEmail(
        email: String,
        password: String,
        onComplete: (Result<String, EmailChangeError>) -> Unit
    ) {
        reAuthenticate(password) { result ->
            when (result) {
                is Result.Success -> {
                    val user = remote.currentUser ?: return@reAuthenticate onComplete(
                        Result.Failure(EmailChangeError.USER_NOT_FOUND)
                    )
                    user.verifyBeforeUpdateEmail(email)
                        .addOnSuccessListener {
                            onComplete(Result.Success(getUserId()!!))
                        }
                        .addOnFailureListener { exception ->
                            val error = when (exception) {
                                is FirebaseAuthException -> exception.asEmailChangeError()
                                else -> EmailChangeError.UNKNOWN
                            }
                            onComplete(Result.Failure(error))
                        }
                }

                is Result.Failure -> onComplete(Result.Failure(result.error.asEmailChangeError()))
            }
        }
    }

    override fun changePassword(
        password: String,
        newPassword: String,
        onComplete: (Result<String, PasswordChangeError>) -> Unit
    ) {
        reAuthenticate(password) { result ->
            when (result) {
                is Result.Success -> {
                    val user = remote.currentUser ?: return@reAuthenticate onComplete(
                        Result.Failure(PasswordChangeError.USER_NOT_FOUND)
                    )
                    user.updatePassword(newPassword)
                        .addOnSuccessListener {
                            onComplete(Result.Success(getUserId()!!))
                        }
                        .addOnFailureListener { exception ->
                            val error = when (exception) {
                                is FirebaseAuthException -> exception.asPasswordChangeError()
                                else -> PasswordChangeError.UNKNOWN
                            }
                            onComplete(Result.Failure(error))
                        }
                }

                is Result.Failure -> onComplete(Result.Failure(result.error.asPasswordChangeError()))
            }
        }
    }

    override fun signOut() = remote.signOut()

    private fun reAuthenticate(
        password: String,
        callback: (Result<String, AuthenticationError>) -> Unit
    ) {
        if (password.isBlank()) {
            return callback(Result.Failure(AuthenticationError.PASSWORD_EMPTY))
        }
        val user = remote.currentUser
            ?: return callback(Result.Failure(AuthenticationError.USER_NOT_FOUND))
        val email = user.email
        if (email.isNullOrBlank()) {
            return callback(Result.Failure(AuthenticationError.USER_NOT_FOUND))
        }
        val credential = EmailAuthProvider.getCredential(email, password)
        user.reauthenticate(credential)
            .addOnSuccessListener {
                callback(Result.Success(user.uid))
            }
            .addOnFailureListener {
                val error = when (it) {
                    is FirebaseAuthException -> it.asAuthenticationError()
                    else -> AuthenticationError.UNKNOWN
                }
                callback(Result.Failure(error))
            }
    }
}