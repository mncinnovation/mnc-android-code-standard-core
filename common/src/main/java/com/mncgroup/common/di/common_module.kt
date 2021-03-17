package com.mncgroup.common.di

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.mncgroup.common.R
import com.mncgroup.common.repository.UserAccessDAO
import com.mncgroup.common.repository.UserDatabase
import com.mncgroup.common.repository.UserRepository
import com.mncgroup.common.repository.UserRepositoryImpl
import com.mncgroup.core.BuildConfig
import com.mncgroup.core.network.createKeyStore
import com.mncgroup.core.network.createOkHttpClient
import com.mncgroup.core.network.createTrustManager
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * TAG for API logging. Possible to rename by using app name TAG for ex : MetubeApi
 */
// TODO: Rename and change value of TAG_API
const val TAG_API = "AppNameApi"

val networkModule = module {
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

val databaseModule = module {
    fun provideUserDatabase(application: Application): UserDatabase {
        return Room.databaseBuilder(application, UserDatabase::class.java, "db_user")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideUserDao(database: UserDatabase): UserAccessDAO {
        return database.userDao()
    }
    single { provideUserDatabase(androidApplication()) }
    single { provideUserDao(get()) }
}

val commonRepositories = module {
    single { UserRepositoryImpl(get()) as UserRepository }
}

val commonModule = listOf(networkModule, databaseModule, commonRepositories)