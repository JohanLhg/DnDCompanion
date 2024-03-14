package com.jlahougue.authentication_data.source

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class AuthFirebaseDataSource(private val firebaseAuth: FirebaseAuth) : AuthRemoteDataSource {

    override fun getUserId() = firebaseAuth.currentUser?.uid

    override fun register(email: String, password: String, callback: (String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.result?.user?.uid)
            }
    }

    override fun login(email: String, password: String, callback: (String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.result?.user?.uid)
            }
    }

    override fun changeEmail(
        email: String,
        password: String,
        onUserError: () -> Unit,
        onReAuthenticationError: (String) -> Unit,
        onComplete: (String?) -> Unit
    ) {
        reAuthenticate(
            password,
            onUserError,
            onReAuthenticationError
        ) {
            val user = firebaseAuth.currentUser?: return@reAuthenticate onUserError()
            user.verifyBeforeUpdateEmail(email)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        onComplete(getUserId())
                    }
                    else
                        onUserError()
                }
        }
    }

    override fun changePassword(password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.currentUser?.updatePassword(password)
            ?.addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    override fun signOut() = firebaseAuth.signOut()

    private fun reAuthenticate(
        password: String,
        onUserError: () -> Unit,
        onReAuthenticationError: (String) -> Unit,
        onReAuthenticated: () -> Unit
    ) {
        val user = firebaseAuth.currentUser?: return onUserError()
        val email = user.email
        if (email.isNullOrBlank()) return onUserError()
        val credential = EmailAuthProvider.getCredential(email, password)
        user.reauthenticate(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onReAuthenticated()
                else onReAuthenticationError(task.exception?.message ?: "Unknown error")
            }
    }
}