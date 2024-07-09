package com.jlahougue.note.presentation

import com.jlahougue.note.domain.model.Note

sealed interface NoteAction {
    data object OnAddNote : NoteAction
    data class OnDeleteNote(val note: Note) : NoteAction
    data class OnEditNote(val note: Note) : NoteAction
}