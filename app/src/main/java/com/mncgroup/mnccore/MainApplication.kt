package com.mncgroup.mnccore

import android.app.Application
import com.mncgroup.core.util.InternetUtil
import com.mncgroup.mnccore.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        InternetUtil.init(this)

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@MainApplication)
            //use modules
            modules(appModules)
            //inject properties
            properties(
                mapOf("base_url" to BuildConfig.BASE_URL)
            )
        }
    }
}