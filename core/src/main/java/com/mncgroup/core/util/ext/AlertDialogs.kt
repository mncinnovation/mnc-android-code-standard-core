package com.mncgroup.core.util.ext

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.mncgroup.core.R

fun Context.showAppCompatAlert(
    messageToShow: String,
    title: String? = getString(R.string.label_attention)
): AlertDialog = showAppCompatAlert(messageToShow, title) {}

fun Context.showAppCompatAlert(
    messageToShow: String,
    title: String? = getString(R.string.label_attention),
    actionTitle: String? = getString(R.string.ok),
    actionListener: () -> Unit
): AlertDialog = this.let { context ->
    val builder = AlertDialog.Builder(context)
    builder.apply {
        setMessage(messageToShow)
        setTitle(title)
        setPositiveButton(actionTitle) { dialogInterface, i ->
            dialogInterface.dismiss()
            actionListener()
        }
    }
    builder.create()
    builder.show()
}

fun Context.showAppCompatAlertAction(
    messageToShow: String,
    title: String? = getString(R.string.label_attention),
    actionTitle: String? = getString(R.string.ok),
    dismissTitle: String? = getString(R.string.cancel),
    actionListener: () -> Unit
): AlertDialog =
    showAppCompatAlertAction(messageToShow, title, actionTitle, dismissTitle, actionListener) {}

fun Context.showAppCompatAlertAction(
    messageToShow: String,
    title: String? = getString(R.string.label_attention),
    actionTitle: String? = getString(R.string.ok),
    dismissTitle: String? = getString(R.string.cancel),
    actionListener: () -> Unit, dismissListener: () -> Unit
): AlertDialog = this.let { context ->
    val builder = AlertDialog.Builder(context)
    builder.apply {
        setMessage(messageToShow)
        setTitle(title)
        setPositiveButton(actionTitle) { dialogInterface, i ->
            dialogInterface.dismiss()
            actionListener()
        }

        setNegativeButton(dismissTitle) { dialogInterface, i ->
            dialogInterface.dismiss()
            dismissListener()
        }
    }
    builder.create()
    builder.show().apply {
        getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.dust
            )
        )
    }
}