package com.jlahougue.core_data_remote_instance.util

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.FirebaseFirestoreException.Code.ABORTED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.ALREADY_EXISTS
import com.google.firebase.firestore.FirebaseFirestoreException.Code.CANCELLED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.DEADLINE_EXCEEDED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.FAILED_PRECONDITION
import com.google.firebase.firestore.FirebaseFirestoreException.Code.INVALID_ARGUMENT
import com.google.firebase.firestore.FirebaseFirestoreException.Code.NOT_FOUND
import com.google.firebase.firestore.FirebaseFirestoreException.Code.OUT_OF_RANGE
import com.google.firebase.firestore.FirebaseFirestoreException.Code.PERMISSION_DENIED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAUTHENTICATED
import com.google.firebase.firestore.FirebaseFirestoreException.Code.UNAVAILABLE
import com.jlahougue.core_domain.util.RemoteWriteError

fun FirebaseFirestoreException.asRemoteWriteError() = when (this.code) {
    ABORTED,
    CANCELLED -> RemoteWriteError.CANCELLED
    UNAVAILABLE,
    RESOURCE_EXHAUSTED,
    DEADLINE_EXCEEDED -> RemoteWriteError.TIMEOUT
    NOT_FOUND -> RemoteWriteError.NOT_FOUND
    INVALID_ARGUMENT,
    FAILED_PRECONDITION,
    OUT_OF_RANGE -> RemoteWriteError.INVALID_REQUEST
    PERMISSION_DENIED,
    UNAUTHENTICATED -> RemoteWriteError.UNAUTHORIZED
    ALREADY_EXISTS -> RemoteWriteError.ALREADY_EXISTS
    else -> RemoteWriteError.UNKNOWN
}