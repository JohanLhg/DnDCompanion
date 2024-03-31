package com.jlahougue.core_data_remote_instance.util

import com.google.firebase.storage.StorageException
import com.jlahougue.core_domain.util.LoadImageError

fun StorageException.asLoadImageError() = when (this.errorCode) {
    StorageException.ERROR_CANCELED -> LoadImageError.CANCELLED
    StorageException.ERROR_OBJECT_NOT_FOUND -> LoadImageError.NO_IMAGE
    StorageException.ERROR_BUCKET_NOT_FOUND,
    StorageException.ERROR_PROJECT_NOT_FOUND -> LoadImageError.INVALID_URL
    StorageException.ERROR_NOT_AUTHENTICATED -> LoadImageError.NOT_AUTHENTICATED
    StorageException.ERROR_NOT_AUTHORIZED -> LoadImageError.NOT_AUTHORIZED
    else -> LoadImageError.UNKNOWN
}