package com.mncgroup.core.util

import android.content.Context

sealed class ErrorWrapper {

    companion object {
        operator fun invoke(errorMessage: String): ErrorWrapper = DefaultError(errorMessage)
        operator fun invoke(resourceId: Int): ErrorWrapper = ResourceError(resourceId)
        operator fun invoke(resourceId: Int? = null, errorMessage: String? = null): ErrorWrapper = ErrorStateError(resourceId, errorMessage)
    }

    abstract fun withContext(context: Context): String?
    abstract fun isNoError(): Boolean

    private data class DefaultError(val errorMessage: String?) : ErrorWrapper() {
        override fun isNoError(): Boolean = errorMessage == null || errorMessage.isBlank()
        override fun withContext(context: Context): String? = if (errorMessage == null || errorMessage.isBlank()) null else errorMessage
    }

    private data class ResourceError(val resourceId: Int?) : ErrorWrapper() {
        override fun isNoError(): Boolean = resourceId == null || resourceId <= 0
        override fun withContext(context: Context): String? {
            return if (resourceId == null || resourceId <= 0) null else context.getString(resourceId)
        }
    }

    private data class ErrorStateError(val resourceId: Int? = null, val errorMessage: String? = null) : ErrorWrapper() {
        override fun isNoError(): Boolean = (resourceId == null || resourceId == 0) && (errorMessage == null || errorMessage.isBlank())
        override fun withContext(context: Context): String? {
            return when {
                isNoError() -> null
                errorMessage != null && !errorMessage.isBlank() -> errorMessage
                resourceId != null && resourceId > 0 -> context.getString(resourceId)
                else -> null
            }
        }
    }
}