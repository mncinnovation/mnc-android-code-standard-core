@file:Suppress("unused")

package com.mncgroup.core.network

import android.content.Context
import androidx.annotation.RawRes
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

/**
 * To create KeyStore only 1 certificate
 */
fun createKeyStore(context: Context, @RawRes id: Int): KeyStore =
    KeyStore.getInstance(KeyStore.getDefaultType()).apply {
        load(null, null)
        val ca: X509Certificate = context.resources.openRawResource(id).use {
            CertificateFactory.getInstance("X.509").generateCertificate(it) as X509Certificate
        }
        setCertificateEntry("0", ca)
    }

/**
 * To create KeyStore from more than 1 certificates
 */
fun createKeyStores(context: Context, @RawRes id: Int): KeyStore =
    KeyStore.getInstance(KeyStore.getDefaultType()).apply {
        load(null, null)
        val certificates = context.resources.openRawResource(id).use {
            CertificateFactory.getInstance("X.509").generateCertificates(it)
        }
        certificates.forEachIndexed { index, ca ->
            setCertificateEntry("$index", ca)
        }
    }

fun createTrustManager(keyStore: KeyStore? = null): X509TrustManager {
    val trustManagers =
        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
            init(keyStore)
        }.trustManagers
    if (trustManagers.isEmpty() || trustManagers[0] !is X509TrustManager) {
        throw IllegalStateException(
            "Unexpected default trust managers:" + Arrays.toString(
                trustManagers
            )
        )
    }
    return trustManagers[0] as X509TrustManager
}

private fun createSSLSocketFactory(trustManager: X509TrustManager): SSLSocketFactory =
    SSLContext.getInstance("TLS").apply {
        init(null, arrayOf(trustManager), null)
    }.socketFactory

@JvmOverloads
fun createOkHttpClient(
    timeout: Long = 60L, trustManager: X509TrustManager? = null, init: Builder.() -> Builder
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(timeout, TimeUnit.SECONDS)
        .readTimeout(timeout, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .apply {
            if (trustManager != null) {
                sslSocketFactory(createSSLSocketFactory(trustManager), trustManager)
            }
        }.addInterceptor { chain ->
            val newRequest = chain.request()
                .newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Connection", "close")
                .build()
            chain.proceed(newRequest)
        }.init()
        .build()
}

inline fun <reified T> createApi(
    servicePath: String,
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val gson = GsonBuilder()
        .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl + servicePath)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}