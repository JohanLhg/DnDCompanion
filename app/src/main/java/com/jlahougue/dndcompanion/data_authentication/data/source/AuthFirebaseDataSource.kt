package com.jlahougue.dndcompanion.data_authentication.data.source

import com.google.firebase.auth.FirebaseAuth

class AuthFirebaseDataSource(
    private val firebaseAuth: FirebaseAuth
) : AuthRemoteDataSource {

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