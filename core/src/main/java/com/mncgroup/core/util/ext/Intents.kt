package com.mncgroup.core.util.ext

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

object Intents {
    fun createShareIntent(subject: String, text: String?) =
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            text?.let { putExtra(Intent.EXTRA_SUBJECT, it) }
            type = "text/plain"
        }

    fun createOpenAppSettingsIntent(context: Context) =
        Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            addCategory(Intent.CATEGORY_DEFAULT)
            data = Uri.parse("package:" + context.packageName)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
        }

    fun createOpenLocationSettings() = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)

    fun createPlayStoreIntent(context: Context) =
        Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=${context.packageName}"))
            .apply {
                if (isPackageInstalled(context, "com.android.vending")) {
                    setPackage("com.android.vending")
                }
            }

    fun isPackageInstalled(context: Context, targetPackage: String) =
        context.packageManager?.getInstalledApplications(0)?.let { apps ->
            apps.any { it.packageName == targetPackage }
        } ?: false
}