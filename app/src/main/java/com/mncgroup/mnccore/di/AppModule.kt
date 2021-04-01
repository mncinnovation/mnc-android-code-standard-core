package com.mncgroup.mnccore.di

import com.mncgroup.auth.di.authModule
import com.mncgroup.common.di.commonModule
import com.mncgroup.common.di.networkModule
import com.mncgroup.mnccore.ui.MainViewModel
import com.mncgroup.mnccore.ui.dashboard.DashboardViewModel
import com.mncgroup.mnccore.ui.home.HomeViewModel
import com.mncgroup.mnccore.ui.notification.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainFeatures = module {
    viewModel { MainViewModel(get()) }
    viewModel { DashboardViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NotificationsViewModel(get()) }
}

val appModules = listOf(mainFeatures) + commonModule + authModule