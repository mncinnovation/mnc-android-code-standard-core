package com.mncgroup.auth.ui.login

import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.repository.AuthRepository
import com.mncgroup.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : BaseViewModel() {

    fun authLogin(email: String, password : String){
        GlobalScope.launch(Dispatchers.Main){
            val result = repository.requestLogin(LoginRequest(email, password))
            result.handle {
                //handle result ok
            }
        }
    }

}