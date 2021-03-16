package com.mncgroup.auth.repository

import com.mncgroup.auth.model.LoginRequest
import com.mncgroup.auth.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("login")
    fun loginUser(@Body loginRequest: LoginRequest) : Call<LoginResponse>

    @POST("registrasi")
    fun registUser(@Body loginRequest: LoginRequest) : Call<LoginResponse>
}

const val AUTH_PATH = "auth/"