package com.mncgroup.mnccore.di

import com.mncgroup.auth.di.authModule
import com.mncgroup.common.di.commonModule
import com.mncgroup.mnccore.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainFeatures = module {
    viewModel { MainViewModel() }
}

val appModules = listOf(mainFeatures) + commonModule + authModule