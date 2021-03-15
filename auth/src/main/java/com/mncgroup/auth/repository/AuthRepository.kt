package com.mncgroup.auth.repository

import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.model.LoginResponse
import com.mncgroup.common.network.awaitResult
import com.mncgroup.core.network.Result
import com.mncgroup.core.util.runIfConnectedOrResultException

interface AuthRepository {
    suspend fun requestLogin(request: LoginRequest): Result<LoginResponse>
}

class AuthRepositoryImpl(private val authApi: AuthApi) : AuthRepository {
    override suspend fun requestLogin(request: LoginRequest): Result<LoginResponse> {
        return runIfConnectedOrResultException {
            authApi.loginUser(request).awaitResult()
        }
    }

}