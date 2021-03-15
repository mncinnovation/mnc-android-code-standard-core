@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.mncgroup.core.network

import androidx.annotation.Keep
import com.google.gson.Gson
import retrofit2.HttpException

@Suppress("unused")
@Keep
public sealed class Result<out T : Any> {
    /**
     * Successful result of request without errors
     */
    public class Ok<out T : Any>(public val data: T) : Result<T>() {
        override fun toString() = "Result.Ok{data=$data}"
    }

    /**
     * HTTP error
     */
    class Error : Result<Nothing> {

        val exception: HttpException?
        val httpCode: Int
        val errorResponse: ErrorResponse

        constructor(exception: HttpException) : super() {
            this.exception = exception
            val errorBody = exception.response()?.errorBody()?.string()
            errorResponse = try {
                Gson().fromJson<ErrorResponse>(errorBody, ErrorResponse::class.java)
            } catch (e: Throwable) {
                defaultError(exception.response()?.raw()?.request()?.url()?.encodedPath() ?: "")
            }
            this.httpCode = exception.code()
        }

        constructor(httpCode: Int, errorResponse: ErrorResponse) : super() {
            this.exception = null
            this.httpCode = httpCode
            this.errorResponse = errorResponse
        }


        override fun toString() =
            "Result.Error{httpCode=$httpCode, error=$errorResponse, $exception}"
    }

    /**
     * Network exception occurred talking to the server or when an unexpected
     * exception occurred creating the request or processing the response
     */
    class Exception(val exception: Throwable?) : Result<Nothing>() {
        val errorMessage: String
            get() {
                return exception?.message ?: ""
            }

        override fun toString() = "Result.Exception{message=$errorMessage,$exception}"
    }
}