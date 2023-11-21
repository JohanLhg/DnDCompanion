package com.jlahougue.dndcompanion.feature_authentication.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.jlahougue.dndcompanion.feature_authentication.domain.repository.AuthRepository

class AuthRepositoryFirebase(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override fun isLoggedIn() = firebaseAuth.currentUser != null

    override suspend fun register(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    override suspend fun login(email: String, password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    override fun changeEmail(email: String, callback: (Boolean) -> Unit) {
        firebaseAuth.currentUser?.updateEmail(email)
            ?.addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    override fun changePassword(password: String, callback: (Boolean) -> Unit) {
        firebaseAuth.currentUser?.updatePassword(password)
            ?.addOnCompleteListener { task ->
                callback(task.isSuccessful)
            }
    }

    override fun signOut() = firebaseAuth.signOut()
}