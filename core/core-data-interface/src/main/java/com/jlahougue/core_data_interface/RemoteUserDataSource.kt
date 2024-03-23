package com.jlahougue.core_data_interface

import com.jlahougue.core_domain.util.LoadImageError
import com.jlahougue.core_domain.util.response.Result
import java.net.URI

interface RemoteUserDataSource {
    fun characterImageUrl(characterID: Long): String
    fun updateCharacterSheet(characterID: Long, values: Map<String, Any>)
    fun loadImage(
        url: String,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun uploadImage(
        targetUrl: String,
        uri: URI,
        onComplete: (Result<String, LoadImageError>) -> Unit
    )
    fun deleteImage(targetUrl: String)
    val charactersUrl: String
    fun characterUrl(characterID: Long): String
    fun delete(url: String)
}