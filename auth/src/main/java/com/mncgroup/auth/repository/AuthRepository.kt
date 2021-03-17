package com.mncgroup.auth.repository

import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.model.LoginResponse
import com.mncgroup.common.model.UserModel
import com.mncgroup.common.network.awaitResult
import com.mncgroup.core.network.ErrorResponse
import com.mncgroup.core.network.Result
import com.mncgroup.core.network.defaultError
import com.mncgroup.core.util.runIfConnectedOrResultException
import kotlinx.coroutines.delay
import java.sql.Date
import java.util.*

interface AuthRepository {
    suspend fun requestLogin(request: LoginRequest): Result<LoginResponse>
}

class AuthRepositoryImpl(private val authApi: AuthApi) : AuthRepository {
    override suspend fun requestLogin(request: LoginRequest): Result<LoginResponse> {
        return runIfConnectedOrResultException {
            //for test only, return data dummy
            delay(2000)
            if (request.email == "bayu.wijaya@mncgroup.com" && request.password == "bayu.wijaya@mncgroup.com") {
                Result.Ok(
                    LoginResponse(
                        "00", "Success",
                        UserModel(
                            1,
                            "4834389753cxcx9fe",
                            "Bayu",
                            "bayu.wijaya@mncgroup.com",
                            "picture.png"
                        )
                    )
                )
            } else {
                Result.Error(200, defaultError("/login", "01", "Email atau password salah"))
            }

            //if rest api already exist use this
//            authApi.loginUser(request).awaitResult()
        }
    }

}