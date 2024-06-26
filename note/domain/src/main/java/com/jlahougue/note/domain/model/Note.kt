package com.jlahougue.note.domain.model

import androidx.room.Entity

@Entity(
    tableName = "note",
    primaryKeys = ["cid", "id"]
)
data class Note(
    val cid: Long,
    val id: Long,
    val title: String,
    val content: String
)
