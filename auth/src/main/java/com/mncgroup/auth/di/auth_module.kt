package com.mncgroup.auth.di

import com.mncgroup.auth.repository.AUTH_PATH
import com.mncgroup.auth.repository.AuthApi
import com.mncgroup.auth.repository.AuthRepository
import com.mncgroup.auth.repository.AuthRepositoryImpl
import com.mncgroup.auth.ui.login.LoginViewModel
import com.mncgroup.core.network.createApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * inject remote data source for auth api by calling [createApi]
 */
val authRemoteDataSourceModule = module {
    single { createApi<AuthApi>(AUTH_PATH, get(), getProperty("base_url")) }
}

/**
 * inject repository
 */
val authRepository = module {
    single { AuthRepositoryImpl(get(), get()) as AuthRepository }
}

/**
 * inject view model
 */
val authFeatures = module {
    viewModel { LoginViewModel(get(), get()) }
}

val authModule = listOf(authRemoteDataSourceModule, authRepository, authFeatures)