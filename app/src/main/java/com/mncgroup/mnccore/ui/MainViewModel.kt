package com.mncgroup.mnccore.ui

import androidx.lifecycle.LiveData
import com.mncgroup.common.model.UserModel
import com.mncgroup.common.repository.UserRepository
import com.mncgroup.core.ui.BaseViewModel

class MainViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    val userData: LiveData<List<UserModel>> = userRepository.getUserLiveData()

    init {

    }
}