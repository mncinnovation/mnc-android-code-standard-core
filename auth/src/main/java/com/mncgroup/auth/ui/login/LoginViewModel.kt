package com.mncgroup.auth.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.repository.AuthRepository
import com.mncgroup.common.model.UserModel
import com.mncgroup.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : BaseViewModel() {

    private var _userLogin = MutableLiveData<UserModel>()
    val userLogin: LiveData<UserModel> get() = _userLogin

    init {

    }

    fun authLogin(email: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = repository.requestLogin(LoginRequest(email, password))
            result.handle {
                //handle result ok

                _userLogin.postValue(it.user)
            }
        }
    }

}