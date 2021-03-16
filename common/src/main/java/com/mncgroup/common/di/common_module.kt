package com.mncgroup.common.di

import android.util.Log
import com.mncgroup.common.R
import com.mncgroup.core.BuildConfig
import com.mncgroup.core.network.createKeyStore
import com.mncgroup.core.network.createOkHttpClient
import com.mncgroup.core.network.createTrustManager
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * TAG for API logging. Possible to rename by using app name TAG for ex : MetubeApi
 */
// TODO: Rename and change value of TAG_API
const val TAG_API = "AppNameApi"

val commonModule = module {
    /**
     * Injection for keystore
     * TODO: R.raw.ca is an sample SSL certificate. Change parameter [createKeyStore] id by your own project certificate
     */
    single { createKeyStore(androidContext(), R.raw.ca) }

    single { createTrustManager(get()) }

    single {
        createOkHttpClient(180L, get()) {
            addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d(TAG_API, message)
                }

            }).apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
        }
    }


}