package com.jlahougue.dndcompanion.core.data.source.remote.subsource

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.jlahougue.dndcompanion.data_ability.data.source.remote.AbilityFirebaseDataSource
import com.jlahougue.dndcompanion.data_authentication.data.source.AuthFirebaseDataSource
import com.jlahougue.dndcompanion.data_character.data.source.remote.CharacterFirebaseDataSource
import com.jlahougue.dndcompanion.data_character_sheet.data.source.remote.CharacterSheetFirebaseDataSource
import com.jlahougue.dndcompanion.data_character_spell.data.source.remote.CharacterSpellFirebaseDataSource
import com.jlahougue.dndcompanion.data_health.data.source.remote.HealthFirebaseDataSource
import com.jlahougue.dndcompanion.data_skill.data.source.remote.SkillFirebaseDataSource
import com.jlahougue.dndcompanion.data_weapon.data.source.remote.subsource.WeaponFirebaseDataSource

class FirebaseDataSource {

    private val firestore by lazy { FirebaseFirestore.getInstance() }
    private val auth by lazy { FirebaseAuth.getInstance() }
    val storage: FirebaseStorage by lazy { FirebaseStorage.getInstance() }
    val uid
        get() = auth.uid.toString()
    private val userReference: DocumentReference
        get() = firestore.collection(TAG_USERS).document(uid)

    //region Data Access Objects
    val authDao by lazy { AuthFirebaseDataSource(auth) }
    val characterSheetDao by lazy { CharacterSheetFirebaseDataSource(this) }
    val characterDao by lazy { CharacterFirebaseDataSource(this) }
    val healthDao by lazy { HealthFirebaseDataSource(this) }
    val abilityDao by lazy { AbilityFirebaseDataSource(this) }
    val skillDao by lazy { SkillFirebaseDataSource(this) }
    val characterSpellDao by lazy { CharacterSpellFirebaseDataSource(this) }
    val weaponDao by lazy { WeaponFirebaseDataSource(this) }
    //endregion

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