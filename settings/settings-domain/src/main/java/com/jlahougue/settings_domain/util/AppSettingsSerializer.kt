package com.jlahougue.settings_domain.util

import androidx.datastore.core.Serializer
import com.jlahougue.settings_domain.model.AppSettings
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object AppSettingsSerializer : Serializer<AppSettings> {
    override val defaultValue: AppSettings
        get() = AppSettings()

    override suspend fun readFrom(input: InputStream): AppSettings {
        return try {
            Json.decodeFromString(
                deserializer = AppSettings.serializer(),
                string = input.readBytes().toString(Charsets.UTF_8))
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: AppSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = AppSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}