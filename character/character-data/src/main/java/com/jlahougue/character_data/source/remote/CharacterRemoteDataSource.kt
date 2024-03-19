package com.jlahougue.character_data.source.remote

import android.net.Uri
import com.jlahougue.character_domain.model.Character
import com.jlahougue.core_domain.util.LoadImageState
import kotlinx.coroutines.flow.StateFlow

interface CharacterRemoteDataSource {
    fun save(character: Character)
    fun delete(characterID: Long)
    fun loadImage(characterId: Long): StateFlow<LoadImageState>
    fun uploadImage(characterId: Long, uri: Uri): StateFlow<LoadImageState>
}