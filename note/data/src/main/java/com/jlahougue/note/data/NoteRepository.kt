package com.jlahougue.note.data

import com.jlahougue.core_data_interface.RemoteUserDataSource
import com.jlahougue.note.domain.model.Note
import com.jlahougue.note.domain.repository.INoteRepository

class NoteRepository(
    private val remote: RemoteUserDataSource,
    private val local: NoteLocalDataSource
): INoteRepository {

    override suspend fun create(characterId: Long): Long {
        val id = local.getLastId(characterId) + 1
        save(Note(cid = characterId, id = id, title = "", content = ""))
        return id
    }

    override suspend fun save(note: Note) {
        local.insert(note)
        remote.updateDocument(
            remote.characterUrl(note.cid),
            mapOf("notes.${note.id}" to note)
        )
    }

    override suspend fun delete(note: Note) {
        local.delete(note)
        remote.deleteField(
            remote.characterUrl(note.cid),
            "notes.${note.id}"
        )
    }

    override fun get(characterId: Long) = local.get(characterId)
}