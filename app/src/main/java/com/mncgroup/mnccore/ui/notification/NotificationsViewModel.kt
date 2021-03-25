package com.mncgroup.mnccore.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mncgroup.common.repository.UserRepository
import com.mncgroup.core.ui.BaseViewModel

/**
 * An notification view model
 * @param userRepository user repository
 */
class NotificationsViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }

    val text: LiveData<String> = _text

    /**
     * Function logic to logout the user
     */
    fun logoutUser(){
        userRepository.clearUser()
    }
}