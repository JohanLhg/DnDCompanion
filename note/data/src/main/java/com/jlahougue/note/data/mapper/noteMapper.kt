package com.jlahougue.note.data.mapper

import com.jlahougue.note.data.model.Note
import com.jlahougue.note.domain.model.Note as DomainNote

fun Note.toDomain() = DomainNote(
    id = id,
    cid = cid,
    title = title,
    content = content
)

fun List<Note>.toDomain() = map { it.toDomain() }

fun DomainNote.toData() = Note(
    id = id,
    cid = cid,
    title = title,
    content = content
)

fun List<DomainNote>.toData() = map { it.toData() }