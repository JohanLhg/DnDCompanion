package com.jlahougue.dndcompanion.core.data.source.remote.subsources

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FirebaseDataSource {

    private val firestore: FirebaseFirestore
    private val auth: FirebaseAuth
    val storage: FirebaseStorage
    var id = ""
    val uid
        get() = auth.uid.toString()
    val userReference: DocumentReference
        get() = firestore.collection(TAG_USERS).document(uid)

    //region Data Access Objects
    //endregion

    init {
        setID()
        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()
    }

    private fun setID() {
        id = ""
        FirebaseInstallations.getInstance().id
            .addOnSuccessListener { id = it }
    }

    fun characterReference(characterID: Long): DocumentReference {
        return userReference.collection(TAG_CHARACTERS).document(characterID.toString())
    }

    fun updateCharacterSheet(characterID: Long, values: Map<String, Any>) {
        characterReference(characterID).update(values)
    }

    //region Image Functions
    fun uploadImage(imageReference: StorageReference, uri: Uri) {
        imageReference.putFile(uri)
            .addOnSuccessListener {
                //finishTask(UPLOAD_IMAGE)
            }
            .addOnFailureListener {
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