package com.jlahougue.core_data_remote_instance

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseDataSource {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    val auth by lazy { FirebaseAuth.getInstance() }
    val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    val uid
        get() = auth.uid.toString()
    private val userReference: DocumentReference
        get() = firestore.collection(TAG_USERS).document(uid)

    fun characterReferences(): CollectionReference {
        return userReference.collection(TAG_CHARACTERS)
    }

    fun characterReference(characterID: Long): DocumentReference {
        return userReference.collection(TAG_CHARACTERS).document(characterID.toString())
    }

    fun updateCharacterSheet(characterID: Long, values: Map<String, Any>) {
        characterReference(characterID).update(values)
    }

    //region Image Functions
    fun uploadImage(
        imageReference: StorageReference,
        uri: Uri
    ) {
        imageReference.putFile(uri)
            .addOnProgressListener {
                //updateProgress(UPLOAD_IMAGE, it)
            }
            .addOnSuccessListener {
                //finishTask(UPLOAD_IMAGE)
            }
            .addOnFailureListener {
                it.localizedMessage
                //finishTask(UPLOAD_IMAGE)
            }
    }

    fun deleteImage(characterID: Long) {
        val storageRef: StorageReference = storage.reference
        val imageRef: StorageReference = storageRef.child("Images/Characters/$uid/$characterID.png")
        imageRef.delete()
    }
    //endregion

    companion object {
        private const val TAG_USERS = "users"
        private const val TAG_CHARACTERS = "characters"
    }
}