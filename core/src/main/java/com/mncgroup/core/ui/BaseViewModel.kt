package com.mncgroup.core.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mncgroup.core.R
import com.mncgroup.core.network.*
import com.mncgroup.core.repository.AnalyticsRepository
import com.mncgroup.core.repository.EventType
import com.mncgroup.core.util.ErrorWrapper
import com.mncgroup.core.util.NoInternetConnection
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import com.mncgroup.core.util.SingleLiveEvent

open class BaseViewModel(private val analyticsRepository: AnalyticsRepository? = null) :
    ViewModel(),
    CoroutineScope {

    val TAG = "BaseViewModel"
    protected val job = SupervisorJob()
    protected val handler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine Error :${throwable.message}", throwable)
    }
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + handler

    protected val _isLoading: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { value = false }
    val isLoading: LiveData<Boolean> = _isLoading

    protected val _showErrorDialog: SingleLiveEvent<ErrorWrapper> = SingleLiveEvent()
    val showErrorDialog: LiveData<ErrorWrapper> = _showErrorDialog

    protected val _showUpdateDialog: SingleLiveEvent<ErrorWrapper> = SingleLiveEvent()
    val showUpdateDialog: LiveData<ErrorWrapper> = _showUpdateDialog

    protected val _navigateToLogin: SingleLiveEvent<ErrorWrapper> = SingleLiveEvent()
    val navigateToLogin: LiveData<ErrorWrapper> = _navigateToLogin

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }

    protected suspend fun <T : Any> Result<T>.handle(okHandler: suspend CoroutineScope.(T) -> Unit) {
        handle({ onResultError(it) }, okHandler)
    }

    protected suspend fun <T : Any> Result<T>.handle(
        errorHandler: suspend CoroutineScope.(Result.Error) -> Unit,
        okHandler: suspend CoroutineScope.(T) -> Unit
    ) {
        withContext(Dispatchers.Main + handler) {
            when (this@handle) {
                is Result.Ok -> okHandler(this@handle.data)
                is Result.Error -> errorHandler(this@handle)
                is Result.Exception -> onResultException(this@handle)
            }
        }
    }

    protected open fun onResultError(error: Result.Error) {
        _showErrorDialog.postValue(ErrorWrapper(errorMessage = error.errorResponse.error ?: ""))
    }

    protected open fun onResultException(exception: Result.Exception) {
        when (exception.exception) {
            is NotLoggedInException, is DifferentDeviceException -> _navigateToLogin.postValue(
                ErrorWrapper(exception.errorMessage.takeIf { it.isNotBlank() }
                    ?: exception.exception.message ?: ""))
            is NeedUpdateException -> _showUpdateDialog.postValue(ErrorWrapper(exception.errorMessage.takeIf { it.isNotBlank() }
                ?: exception.exception.message ?: ""))
            is NoInternetConnection -> _showErrorDialog.postValue(ErrorWrapper(R.string.label_msg_offline))
            else -> _showErrorDialog.postValue(ErrorWrapper(exception.errorMessage.takeIf { it.isNotBlank() }
                ?: exception.exception?.message ?: ""))
        }
    }

    fun logEvent(name: String, eventType: EventType, vararg args: Pair<String, String>) {
        analyticsRepository?.logEvent(name, eventType, *args)
    }
}