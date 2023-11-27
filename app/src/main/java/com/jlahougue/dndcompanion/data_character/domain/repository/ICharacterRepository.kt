package com.jlahougue.dndcompanion.data_character.domain.repository

import com.jlahougue.dndcompanion.data_character.domain.model.Character

interface ICharacterRepository {
    suspend fun create(): Boolean
    suspend fun save(character: Character)
    suspend fun saveToLocal(character: Character)
    suspend fun delete(id: Long)
}