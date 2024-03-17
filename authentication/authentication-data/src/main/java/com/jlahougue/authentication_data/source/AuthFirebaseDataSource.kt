package com.jlahougue.authentication_data.source

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.jlahougue.authentication_domain.util.AuthenticationError
import com.jlahougue.authentication_domain.util.EmailChangeError
import com.jlahougue.core_domain.util.response.Result

class AuthFirebaseDataSource(private val firebaseAuth: FirebaseAuth) : AuthRemoteDataSource {

    override fun getUserId() = firebaseAuth.currentUser?.uid

    override fun register(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(Result.Success(it.user!!.uid))
            }
            .addOnFailureListener {
                val error = when (it) {
                    is FirebaseAuthException -> {
                        when (it.errorCode) {
                            "ERROR_USER_NOT_FOUND" -> AuthenticationError.USER_NOT_FOUND
                            "ERROR_INVALID_CREDENTIAL" -> AuthenticationError.INVALID_CREDENTIAL
                            "ERROR_EMAIL_ALREADY_IN_USE" -> AuthenticationError.EMAIL_ALREADY_IN_USE
                            "ERROR_INVALID_EMAIL" -> AuthenticationError.EMAIL_INVALID
                            "ERROR_WEAK_PASSWORD" -> AuthenticationError.WEAK_PASSWORD
                            else -> AuthenticationError.UNKNOWN
                        }
                    }
                    else -> AuthenticationError.UNKNOWN
                }
                onComplete(Result.Failure(error))
            }
    }

    override fun login(
        email: String,
        password: String,
        onComplete: (Result<String, AuthenticationError>) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onComplete(Result.Success(it.user!!.uid))
            }
            .addOnFailureListener {
                val error = when (it) {
                    is FirebaseAuthException -> {
                        when (it.errorCode) {
                            "ERROR_USER_NOT_FOUND" -> AuthenticationError.USER_NOT_FOUND
                            "ERROR_INVALID_CREDENTIAL" -> AuthenticationError.INVALID_CREDENTIAL
                            else -> AuthenticationError.UNKNOWN
                        }
                    }
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
                    val user = firebaseAuth.currentUser?: return@reAuthenticate onComplete(Result.Failure(EmailChangeError.USER_NOT_FOUND))
                    user.verifyBeforeUpdateEmail(email)
                        .addOnSuccessListener {
                            onComplete(Result.Success(getUserId()!!))
                        }
                        .addOnFailureListener { exception ->
                            val error = when (exception) {
                                is FirebaseAuthException -> {
                                    when (exception.errorCode) {
                                        "ERROR_USER_NOT_FOUND" -> EmailChangeError.USER_NOT_FOUND
                                        "ERROR_INVALID_CREDENTIAL" -> EmailChangeError.INVALID_CREDENTIAL
                                        "ERROR_EMAIL_ALREADY_IN_USE" -> EmailChangeError.EMAIL_ALREADY_IN_USE
                                        "ERROR_INVALID_EMAIL" -> EmailChangeError.EMAIL_INVALID
                                        "ERROR_EMAIL_EMPTY" -> EmailChangeError.EMAIL_EMPTY
                                        else -> EmailChangeError.UNKNOWN
                                    }
                                }
                                else -> EmailChangeError.UNKNOWN
                            }
                            onComplete(Result.Failure(error))
                        }
                }
                is Result.Failure -> {
                    val error = when (result.error) {
                        AuthenticationError.PASSWORD_EMPTY -> EmailChangeError.PASSWORD_EMPTY
                        AuthenticationError.USER_NOT_FOUND -> EmailChangeError.USER_NOT_FOUND
                        AuthenticationError.INVALID_CREDENTIAL -> EmailChangeError.INVALID_CREDENTIAL
                        else -> EmailChangeError.ERROR_RE_AUTHENTICATING
                    }
                    onComplete(Result.Failure(error))
                }
            }
        }
    }

    override fun changePassword(password: String, onComplete: (Boolean) -> Unit) {
        firebaseAuth.currentUser?.updatePassword(password)
            ?.addOnCompleteListener { task ->
                onComplete(task.isSuccessful)
            }
    }

    override fun signOut() = firebaseAuth.signOut()

    private fun reAuthenticate(
        password: String,
        callback: (Result<String, AuthenticationError>) -> Unit
    ) {
        if (password.isBlank()) {
            return callback(Result.Failure(AuthenticationError.PASSWORD_EMPTY))
        }
        val user = firebaseAuth.currentUser?: return callback(Result.Failure(AuthenticationError.USER_NOT_FOUND))
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
                    is FirebaseAuthException -> {
                        when (it.errorCode) {
                            "ERROR_USER_NOT_FOUND" -> AuthenticationError.USER_NOT_FOUND
                            "ERROR_INVALID_CREDENTIAL" -> AuthenticationError.INVALID_CREDENTIAL
                            else -> AuthenticationError.UNKNOWN
                        }
                    }
                    else -> AuthenticationError.UNKNOWN
                }
                callback(Result.Failure(error))
            }
    }
}