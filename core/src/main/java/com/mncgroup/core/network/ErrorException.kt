package com.mncgroup.core.network

import androidx.annotation.Keep
import java.util.*

/**
 * An error response data class
 * @param timestamp date time of error response
 * @param status string status of error response
 * @param error error value
 * @param message error message description
 * @param path path of url request
 */
data class ErrorResponse(val timestamp: Date, val status: String,
                         val error: String?,
                         private val message: String, val path: String)

fun defaultError(path: String, status: String = "500", message: String = "Internal Server Error") = ErrorResponse(Date(), status, message, message, path)

@Keep
class DifferentDeviceException(message: String) : Exception(message)

/**
 * An session expired exception
 * @param message message of session expired
 */
@Keep
class SessionExpiredException(message: String) : Exception(message)

/**
 * An not loggedin exception
 * @param message message of not loggedin exception
 */
@Keep
class NotLoggedInException(message: String = "Kamu belum login, silakan login terlebih dahulu") : Exception(message)

/**
 * An user banned exception
 * @param message message of banned exception
 */
@Keep
class BannedException(message: String) : Exception(message)

/**
 * Need update exception
 * @param message message of need update exception
 */
@Keep
class NeedUpdateException(message: String) : Exception(message)