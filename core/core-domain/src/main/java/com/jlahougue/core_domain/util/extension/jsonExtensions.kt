package com.jlahougue.core_domain.util.extension

import org.json.JSONArray
import org.json.JSONObject

fun JSONObject.getStringIfExists(key: String): String {
    return if (this.has(key) && !this.isNull(key)) this.getString(key) else ""
}

fun JSONObject.getIntIfExists(key: String): Int {
    return if (this.has(key)) this.getInt(key) else 0
}

fun JSONObject.getJSONArrayIfExists(key: String): JSONArray {
    if (this.has(key) && !this.isNull(key)) {
        return this.getJSONArray(key)
    }
    return JSONArray()
}