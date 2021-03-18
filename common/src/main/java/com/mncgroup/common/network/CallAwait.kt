@file:Suppress("unused")

package com.mncgroup.common.network

import com.mncgroup.common.model.BaseResponseModel
import com.mncgroup.common.network.ConstantApi.Companion.API_ERROR_DEVICE
import com.mncgroup.common.network.ConstantApi.Companion.API_ERROR_LOGGED_OUT
import com.mncgroup.common.network.ConstantApi.Companion.API_ERROR_NEED_UPDATE
import com.mncgroup.common.network.ConstantApi.Companion.API_ERROR_SESSION_EXPIRED
import com.mncgroup.common.network.ConstantApi.Companion.API_OK
import com.mncgroup.core.network.*
import com.mncgroup.core.network.Result
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

fun <T : BaseResponseModel> Call<T>.awaitDeferred(coroutineScope: CoroutineScope = GlobalScope): Deferred<Result<T>> {
    return coroutineScope.async {
        try {
            val response = execute()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    when {
                        body == null -> Result.Exception(NullPointerException("Response Body is null"))
                        body.responseCode == API_OK -> Result.Ok(body)
                        body.responseCode == API_ERROR_DEVICE -> Result.Exception(
                            DifferentDeviceException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_LOGGED_OUT -> Result.Exception(
                            NotLoggedInException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_SESSION_EXPIRED -> Result.Exception(
                            SessionExpiredException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_NEED_UPDATE -> Result.Exception(
                            NeedUpdateException(body.responseMessage)
                        )
                        else -> Result.Error(
                            response.code(), defaultError(
                                response.raw().request().url().encodedPath(),
                                body.responseCode,
                                body.responseMessage
                            )
                        )
                    }
                }
                else -> Result.Error(HttpException(response))
            }
        } catch (e: Throwable) {
            Result.Exception(e)
        }
    }
}

fun <T : BaseResponseModel, R : Any> Call<T>.awaitDeferred(
    coroutineScope: CoroutineScope = GlobalScope,
    transform: T.() -> R
): Deferred<Result<R>> {
    return coroutineScope.async {
        try {
            val response = execute()
            when {
                response.isSuccessful -> {
                    val body = response.body()
                    when {
                        body == null -> Result.Exception(NullPointerException("Response Body is null"))
                        body.responseCode == API_OK -> Result.Ok(body.transform())
                        body.responseCode == API_ERROR_DEVICE -> Result.Exception(
                            DifferentDeviceException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_LOGGED_OUT -> Result.Exception(
                            NotLoggedInException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_SESSION_EXPIRED -> Result.Exception(
                            SessionExpiredException(body.responseMessage)
                        )
                        body.responseCode == API_ERROR_NEED_UPDATE -> Result.Exception(
                            NeedUpdateException(body.responseMessage)
                        )
//                        body.responseCode == API_ERROR_BANNED -> Result.Exception(BannedException(body.responseMessage))
                        else -> Result.Error(
                            response.code(), defaultError(
                                response.raw().request().url().encodedPath(),
                                body.responseCode,
                                body.responseMessage
                            )
                        )
                    }
                }
                else -> Result.Error(HttpException(response))
            }
        } catch (e: Throwable) {
            Result.Exception(e)
        }
    }
}

@ExperimentalCoroutinesApi
suspend fun <T : Any> Call<T>.awaitResultAny(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    when {
                        response.isSuccessful -> {
                            val body = response.body()
                            when (body) {
                                null -> Result.Exception(NullPointerException("Response body is null"))
                                else -> Result.Ok(body)
                            }
                        }
                        else -> Result.Error(HttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                val result = Result.Exception(t)
                continuation.resume(result, null)
            }

        })

        registerOnCompletion(continuation)
    }
}

@ExperimentalCoroutinesApi
suspend fun <T : Any, R : Any> Call<T>.awaitResultAny(transform: T.() -> R): Result<R> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    when {
                        response.isSuccessful -> {
                            val body = response.body()
                            when (body) {
                                null -> Result.Exception(NullPointerException("Response body is null"))
                                else -> Result.Ok(body.transform())
                            }
                        }
                        else -> Result.Error(HttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                val result = Result.Exception(t)
                continuation.resume(result, null)
            }

        })

        registerOnCompletion(continuation)
    }
}

/**
 * Suspend extension that allows suspend [Call] inside coroutine.
 *
 * @return sealed class [Result] object that can be
 *         casted to [Result.Ok] (success) or [Result.Error] (HTTP error) and [Result.Exception] (other errors)
 */
suspend fun <T : BaseResponseModel> Call<T>.awaitResult(): Result<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    when {
                        response.isSuccessful -> {
                            val body = response.body()
                            when {
                                body == null -> Result.Exception(NullPointerException("Response body is null"))
                                body.responseCode == API_OK -> Result.Ok(body)
                                body.responseCode == API_ERROR_DEVICE -> Result.Exception(
                                    DifferentDeviceException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_LOGGED_OUT -> Result.Exception(
                                    NotLoggedInException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_SESSION_EXPIRED -> Result.Exception(
                                    SessionExpiredException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_NEED_UPDATE -> Result.Exception(
                                    NeedUpdateException(body.responseMessage)
                                )
//                            body.responseCode == API_ERROR_BANNED -> Result.Exception(BannedException(body.responseMessage))
                                else -> Result.Error(
                                    response.code(), defaultError(
                                        response.raw().request().url().encodedPath(),
                                        body.responseCode,
                                        body.responseMessage
                                    )
                                )
                            }
                        }
                        else -> Result.Error(HttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                val result = Result.Exception(t)
                continuation.resume(result, null)
            }

        })

        registerOnCompletion(continuation)
    }
}

/**
 * Suspend extension that allows suspend [Call] inside coroutine.
 *
 * @return sealed class [Result] object that can be
 *         casted to [Result.Ok] (success) or [Result.Error] (HTTP error) and [Result.Exception] (other errors)
 */
@ExperimentalCoroutinesApi
suspend fun <T : BaseResponseModel, R : Any> Call<T>.awaitResult(transform: T.() -> R): Result<R> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resumeWith(runCatching {
                    when {
                        response.isSuccessful -> {
                            val body = response.body()
                            when {
                                body == null -> Result.Exception(NullPointerException("Response body is null"))
                                body.responseCode == API_OK -> Result.Ok(body.transform())
                                body.responseCode == API_ERROR_DEVICE -> Result.Exception(
                                    DifferentDeviceException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_LOGGED_OUT -> Result.Exception(
                                    NotLoggedInException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_SESSION_EXPIRED -> Result.Exception(
                                    SessionExpiredException(body.responseMessage)
                                )
                                body.responseCode == API_ERROR_NEED_UPDATE -> Result.Exception(
                                    NeedUpdateException(body.responseMessage)
                                )
//                            body.responseCode == API_ERROR_BANNED -> Result.Exception(BannedException(body.responseMessage))
                                else -> Result.Error(
                                    response.code(), defaultError(
                                        response.raw().request().url().encodedPath(),
                                        body.responseCode,
                                        body.responseMessage
                                    )
                                )
                            }
                        }
                        else -> Result.Error(HttpException(response))
                    }
                })
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                // Don't bother with resuming the continuation if it is already cancelled.
                if (continuation.isCancelled) return
                val result = Result.Exception(t)
                continuation.resume(result, null)
            }

        })

        registerOnCompletion(continuation)
    }
}

private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
            //Ignore cancel exception
        }
    }
}