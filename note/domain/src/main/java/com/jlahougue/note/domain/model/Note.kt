package com.jlahougue.note.domain.model

data class Note(
    val id: Long = 0,
    val cid: Long = 0,
    val title: String = "",
    val content: String = ""
) {
    override fun toString(): String {
        return """
            $title
            $content
        """.trimIndent()
    }
}
