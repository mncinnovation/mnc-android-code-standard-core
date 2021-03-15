package com.mncgroup.core.util.ext

import android.annotation.TargetApi
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

@TargetApi(Build.VERSION_CODES.N)
private fun Context.updateResourcesLocale(locale: Locale): Context {
    val configuration = resources.configuration
    configuration.setLocale(locale)
    return createConfigurationContext(configuration)
}

@Suppress("DEPRECATION")
private fun Context.updateResourcesLocaleLegacy(locale: Locale): Context {
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
    return this
}

fun Context.updateBaseContextLocale(locale: Locale): Context {
    Locale.setDefault(locale)

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        updateResourcesLocale(locale)
    } else updateResourcesLocaleLegacy(locale)

}

@Suppress("DEPRECATION")
private fun Context.updateResourcesLocale(body: Configuration.() -> Unit): Context {
    val configuration = resources.configuration
    body(configuration)
    resources.updateConfiguration(configuration, resources.displayMetrics)
    return this
}

@Suppress("DEPRECATION")
fun Context.updateBaseContextLocaleWithoutChange(locale: Locale): Context {
    Locale.setDefault(locale)
    return updateResourcesLocale {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setLocale(locale)
        } else this.locale = locale
    }
}