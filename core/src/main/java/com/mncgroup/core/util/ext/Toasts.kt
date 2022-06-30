package com.mncgroup.core.util.ext

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.Toast

fun Context.showToastCustom(
    layout: View? = null,
    gravity: Int? = Gravity.TOP,
    xOffset: Int? = 0,
    yOffset: Int? = 120
) {
    val toast = Toast(this)
    gravity?.let {
        toast.setGravity(it, xOffset ?: 0, yOffset ?: 120)
    }
    toast.duration = Toast.LENGTH_LONG
    layout?.let {
        toast.view = it
    }
    toast.show()
}

fun Context.showToast(message: String, duration: Int? = Toast.LENGTH_LONG) {
    Toast.makeText(this, message, duration ?: Toast.LENGTH_LONG).show()
}
