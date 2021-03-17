package com.mncgroup.auth.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.repository.AuthRepository
import com.mncgroup.common.model.UserModel
import com.mncgroup.common.repository.UserRepository
import com.mncgroup.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {


    val userData: LiveData<List<UserModel>> = userRepository.getUserLiveData()

    init {

    }

    fun authLogin(email: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = authRepository.requestLogin(LoginRequest(email, password))
            result.handle {
                //other handle result ok

            }
        }
    }

}