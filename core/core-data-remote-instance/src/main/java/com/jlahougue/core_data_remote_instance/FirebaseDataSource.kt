package com.jlahougue.core_data_remote_instance

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageException
import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.core_data_remote_instance.util.asLoadImageError
import com.jlahougue.core_data_remote_instance.util.asRemoteReadError
import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.RemoteReadError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

class FirebaseDataSource : RemoteUserDataSource {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    val auth by lazy { FirebaseAuth.getInstance() }
    private val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    private val uid
        get() = auth.uid.toString()

    override fun characterImageUrl(characterID: Long) = "Images/Characters/$uid/$characterID.png"

    override val charactersUrl = "$TAG_USERS/$uid/$TAG_CHARACTERS"

    override fun characterUrl(characterID: Long) = "$TAG_USERS/$uid/$TAG_CHARACTERS/$characterID"

    private fun characterReference(characterID: Long): DocumentReference {
        return firestore.document(characterUrl(characterID))
    }

    fun updateCharacterSheet(characterID: Long, values: Map<String, Any>) {
        characterReference(characterID).update(values)
    }

    override fun updateDocument(
        url: String,
        values: Map<String, Any>
    ) {
        firestore.document(url).update(values)
    }

    override fun <T> getList(
        url: String,
        type: Class<T>,
        onComplete: (Result<List<T>, RemoteReadError>) -> Unit
    ) {
        firestore.collection(url).get()
            .addOnCanceledListener {
                onComplete(Result.Failure(RemoteReadError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = (exception as FirebaseFirestoreException).asRemoteReadError()
                onComplete(Result.Failure(error))
            }
            .addOnSuccessListener { documents ->
                val characters = mutableListOf<T>()
                for (document in documents) {
                    val character = document.toObject(type)
                    characters.add(character)
                }
                onComplete(Result.Success(characters))
            }
    }

    override fun delete(url: String) {
        firestore.document(url).delete()
    }

    //region Image Functions
    override fun loadImage(
        url: String,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        storage.reference.child(url)
            .downloadUrl
            .addOnSuccessListener { uri ->
                onComplete(Result.Success(uri.toString()))
            }
            .addOnCanceledListener {
                onComplete(Result.Failure(LoadImageError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = (exception as StorageException).asLoadImageError()
                onComplete(Result.Failure(error))
            }
    }

    override fun uploadImage(
        targetUrl: String,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    ) {
        storage.reference.child(targetUrl)
            .putFile(Uri.parse(uri.toString()))
            .addOnSuccessListener {
                onComplete(Result.Success(uri.toString()))
            }
            .addOnCanceledListener {
                onComplete(Result.Failure(LoadImageError.CANCELLED))
            }
            .addOnFailureListener { exception ->
                val error = (exception as StorageException).asLoadImageError()
                onComplete(Result.Failure(error))
            }
    }

    override fun deleteImage(targetUrl: String) {
        storage.reference.child(targetUrl).delete()
    }
    //endregion

    companion object {
        private const val TAG_USERS = "users"
        private const val TAG_CHARACTERS = "characters"
    }
}