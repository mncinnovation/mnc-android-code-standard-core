package com.mncgroup.core.util.ext

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun Activity.requestPermissions(requestCode: Int, vararg permissions: String) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}


fun Activity.checkPermissionsAny(vararg permissions: String): PermissionResult {
    return if (permissions.any { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
        PermissionResult.Granted
    } else {
        if (permissions.any { ActivityCompat.shouldShowRequestPermissionRationale(this, it) }) {
            PermissionResult.DialogNeeded
        } else {
            PermissionResult.NeedRequest
        }
    }
}

fun Activity.checkPermissionsAll(permissions: List<String>): PermissionResult {
    return if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
        PermissionResult.Granted
    } else {
        if (permissions.all { ActivityCompat.shouldShowRequestPermissionRationale(this, it) }) {
            PermissionResult.DialogNeeded
        } else {
            PermissionResult.NeedRequest
        }
    }
}

sealed class PermissionResult {
    object Granted : PermissionResult()
    object DialogNeeded : PermissionResult()
    object NeedRequest : PermissionResult()
}