package com.jlahougue.core_data_interface.util

import com.jlahougue.core_domain.util.RemoteReadError

fun Int.asRemoteReadError() = when (this) {
    400, 412, 413, 414, 415 -> RemoteReadError.INVALID_REQUEST
    401, 403, 405, 406, 407 -> RemoteReadError.UNAUTHORIZED
    404 -> RemoteReadError.NOT_FOUND
    408 -> RemoteReadError.TIMEOUT
    else -> RemoteReadError.UNKNOWN
}