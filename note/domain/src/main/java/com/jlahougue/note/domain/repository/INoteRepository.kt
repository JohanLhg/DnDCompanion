package com.jlahougue.note.domain.repository

import com.jlahougue.note.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface INoteRepository {
    suspend fun create(characterId: Long): Long
    suspend fun save(note: Note)
    suspend fun delete(note: Note)
    fun get(characterId: Long): Flow<List<Note>>
}