package com.jlahougue.note.data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.note.data.mapper.toData
import com.jlahougue.note.data.mapper.toDomain
import com.jlahougue.note.domain.model.Note
import com.jlahougue.note.domain.repository.INoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepository(
    private val remote: RemoteUserDataSource,
    private val local: NoteLocalDataSource
): INoteRepository {

    override suspend fun create(characterId: Long) {
        save(Note(cid = characterId))
    }

    override suspend fun save(note: Note) {
        local.insert(note.toData())
        remote.updateDocument(
            remote.characterUrl(note.cid),
            mapOf("notes.${note.id}" to note)
        )
    }

    override suspend fun saveToLocal(notes: List<Note>) {
        local.insert(notes.toData())
    }

    override suspend fun delete(note: Note) {
        local.delete(note.toData())
        remote.deleteField(
            remote.characterUrl(note.cid),
            "notes.${note.id}"
        )
    }

    override fun get(characterId: Long): Flow<List<Note>> {
        return local.get(characterId)
            .map { it.toDomain() }
    }
}