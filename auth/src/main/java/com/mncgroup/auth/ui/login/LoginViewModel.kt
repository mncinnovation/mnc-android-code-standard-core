package com.mncgroup.auth.ui.login

import com.mncgroup.auth.repository.AuthRepository
import com.mncgroup.core.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel(val repository: AuthRepository) : BaseViewModel() {

    fun authLogin(){
        GlobalScope.launch(Dispatchers.Main){

        }
    }

}