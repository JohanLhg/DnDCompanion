package com.jlahougue.core_domain.util.extension

import org.json.JSONArray
import org.json.JSONObject

/**
 * Get the string value associated with a key.
 *
 * @param key
 *            A key string.
 * @return The string value, an empty string if the key doesn't exist.
 */
fun JSONObject.getStringIfExists(key: String): String {
    return if (this.has(key) && !this.isNull(key)) this.getString(key) else ""
}

/**
 * Get the int value associated with a key.
 *
 * @param key
 *            A key string.
 * @return The integer value, 0 if the key doesn't exist.
 */
fun JSONObject.getIntIfExists(key: String): Int {
    return if (this.has(key)) this.getInt(key) else 0
}

/**
 * Get the JSONArray value associated with a key.
 *
 * @param key
 *            A key string.
 * @return A JSONArray which is the value, an empty JSONArray if the key doesn't exist.
 */
fun JSONObject.getJSONArrayIfExists(key: String): JSONArray {
    if (this.has(key) && !this.isNull(key)) {
        return this.getJSONArray(key)
    }
    return JSONArray()
}

/**
 * Performs the given [action] on each JSONObject in the JSONArray.
 */
fun JSONArray.forEachObject(action: (JSONObject) -> Unit) {
    for (i in 0 until this.length()) {
        action(this.getJSONObject(i))
    }
}