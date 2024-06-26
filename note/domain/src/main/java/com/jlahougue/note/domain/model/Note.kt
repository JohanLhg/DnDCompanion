package com.jlahougue.note.domain.model

import androidx.room.Entity

@Entity(
    tableName = "note",
    primaryKeys = ["cid", "title"]
)
data class Note(
    val cid: Long = 0,
    val title: String = "",
    val content: String = ""
)
