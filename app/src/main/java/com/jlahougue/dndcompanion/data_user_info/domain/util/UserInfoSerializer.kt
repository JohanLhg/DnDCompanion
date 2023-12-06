package com.jlahougue.dndcompanion.data_user_info.domain.util

import androidx.datastore.core.Serializer
import com.jlahougue.dndcompanion.data_user_info.domain.model.UserInfo
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

object UserInfoSerializer : Serializer<UserInfo> {
    override val defaultValue: UserInfo
        get() = UserInfo()

    override suspend fun readFrom(input: InputStream): UserInfo {
        return try {
            Json.decodeFromString(
                deserializer = UserInfo.serializer(),
                string = input.readBytes().toString(Charsets.UTF_8))
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserInfo, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserInfo.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}