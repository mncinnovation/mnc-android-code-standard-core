package com.mncgroup.mnccore

import android.app.Application
import com.mncgroup.core.util.InternetUtil
import com.mncgroup.mnccore.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * An application class of project
 *
 * TODO: Rename with project name application, for ex : MetubeApplication
 */
class AppNameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //init internet util
        InternetUtil.init(this)

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@AppNameApplication)
            //use modules
            modules(appModules)
            //inject properties
            properties(
                mapOf("base_url" to BuildConfig.BASE_URL)
            )
        }
    }
}