package com.jlahougue.core_domain.util.extension

import com.google.firebase.firestore.DocumentSnapshot

val DocumentSnapshot.size: Int
    get() {
        val data = this.data ?: return 0
        return data.fstSize()
    }

private fun Any.fstSize(): Int {
    return when (this) {
        is String -> this.length + 1
        is Int -> 8
        is Long -> 8
        is Double -> 8
        is Boolean -> 1
        is Map<*, *> -> {
            var netSizeInBytes = 0;
            this.entries.forEach { entry ->
                netSizeInBytes += entry.key?.fstSize() ?: 0;
                netSizeInBytes += entry.value?.fstSize() ?: 0;
            }
            netSizeInBytes
        }
        is List<*> -> {
            var netSizeInBytes = 0;
            this.forEach { item ->
                netSizeInBytes += item?.fstSize() ?: 0;
            }
            netSizeInBytes
        }
        else -> 0
    }
}