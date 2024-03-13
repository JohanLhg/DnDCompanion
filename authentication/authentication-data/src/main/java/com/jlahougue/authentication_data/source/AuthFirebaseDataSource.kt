package com.jlahougue.authentication_data.source

import com.google.firebase.auth.FirebaseAuth

class AuthFirebaseDataSource(private val firebaseAuth: FirebaseAuth) : AuthRemoteDataSource {

    override fun getUserId() = firebaseAuth.currentUser?.uid

    override suspend fun register(email: String, password: String, callback: (String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.result?.user?.uid)
            }
    }

    override suspend fun login(email: String, password: String, callback: (String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                callback(task.result?.user?.uid)
            }
    }

    override fun changeEmail(email: String, callback: (String?) -> Unit) {
        firebaseAuth.currentUser?.verifyBeforeUpdateEmail(email)
            ?.addOnCompleteListener {
                callback(getUserId())
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