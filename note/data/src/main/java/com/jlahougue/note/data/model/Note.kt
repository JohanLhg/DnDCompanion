package com.jlahougue.note.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val cid: Long = 0,
    val title: String = "",
    val content: String = ""
)
