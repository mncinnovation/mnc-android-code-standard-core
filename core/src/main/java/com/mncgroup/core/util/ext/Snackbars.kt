package com.mncgroup.core.util.ext

import android.content.Context
import android.view.View

import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

import com.google.android.material.snackbar.Snackbar
import com.mncgroup.core.R


fun Context.showSnackbar(
    view: View,
    message: String,
    duration: Int? = null,
    @ColorRes bgColor: Int? = null,
    @ColorRes actionTextColor: Int? = null,
    actionTitle: String? = getString(R.string.ok),
    actionListener: () -> Unit
) {
    val snackbar = Snackbar.make(view, message, duration ?: Snackbar.LENGTH_SHORT)
    actionTextColor?.let {
        snackbar.setActionTextColor(it)
    }
    snackbar.setAction(
        actionTitle ?: getString(R.string.ok)
    ) { actionListener() }

    val group = snackbar.view as ViewGroup
    bgColor?.let {
        group.setBackgroundColor(ContextCompat.getColor(this, it))
    }

    snackbar.show()
}