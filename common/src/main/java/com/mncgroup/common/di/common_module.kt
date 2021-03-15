package com.mncgroup.common.di

import android.util.Log
import com.mncgroup.common.R
import com.mncgroup.core.BuildConfig
import com.mncgroup.core.network.createKeyStores
import com.mncgroup.core.network.createOkHttpClient
import com.mncgroup.core.network.createTrustManager
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Possible to rename by using app name TAG
 */
const val TAG_API = "MNCApi"

val commonModule = module {
    /**
     * TODO : R.raw.ca is an SSL certificate that need to replacing by your own certificate
     */
    single { createKeyStores(androidContext(), R.raw.ca) }

    single { createTrustManager(get()) }

    single {
        createOkHttpClient(180L, get()) {
            addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d(TAG_API, "log: $message")
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