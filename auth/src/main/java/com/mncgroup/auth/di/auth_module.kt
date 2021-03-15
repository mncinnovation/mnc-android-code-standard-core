package com.mncgroup.auth.di

import com.mncgroup.auth.repository.AUTH_PATH
import com.mncgroup.auth.repository.AuthApi
import com.mncgroup.core.network.createApi
import org.koin.dsl.module

val authRemoteDataSourceModule = module {
    single { createApi<AuthApi>(AUTH_PATH, get(), getProperty("base_url")) }
}

val authModule = listOf(authRemoteDataSourceModule)