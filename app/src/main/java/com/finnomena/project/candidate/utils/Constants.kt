package com.finnomena.project.candidate.utils

class Constants {
    companion object {
        const val HTTP_OK = 200
        const val HTTP_UNAUTHORIZED = 401
        const val HTTP_FORBIDDEN = 403
        const val HTTP_ERROR = 400
        const val HTTP_NOCONTENT = 204
        const val HTTP_INTERNAL_ERROR = 500

        const val VIEWTYPE_EMPTY = 0x0200
        const val VIEWTYPE_NORMAL = 0x0201

        const val SOCKET_TIMEOUT = "java.net.SocketTimeoutException"

    }
}