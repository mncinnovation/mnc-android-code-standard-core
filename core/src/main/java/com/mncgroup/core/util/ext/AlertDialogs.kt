package com.mncgroup.core.util.ext

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputLayout
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

fun Context.showAppCompatAlertInputAction(
    messageToShow: String? = null,
    title: String? = null,
    actionTitle: String? = getString(R.string.title_submit),
    negativeTitle: String? = getString(R.string.cancel),
    textHint: String? = null, cancelable: Boolean = false,
    actionListener: (input: String) -> Unit
): AlertDialog = showAppCompatAlertInputAction(messageToShow, title, actionTitle, negativeTitle, textHint, cancelable, actionListener, {})

fun Context.showAppCompatAlertInputAction(
    messageToShow: String? = null,
    title: String? = null,
    actionTitle: String? = getString(R.string.title_submit),
    negativeTitle: String? = getString(R.string.cancel),
    textHint: String? = null, cancelable: Boolean = false,
    actionListener: (input: String) -> Unit,
    dismissListener: () -> Unit = {}
): AlertDialog = this.let { context ->
    val builder = AlertDialog.Builder(context)
    builder.apply {
        if (title != null) {
            this.setTitle(title)
        }
        if (messageToShow != null) {
            this.setMessage(messageToShow)
        }
        val layoutInflater =
            getSystemService(Context.LAYOUT_INFLATER_SERVICE) as android.view.LayoutInflater
        val textInput = layoutInflater.inflate(R.layout.dialog_input, null) as TextInputLayout
        textInput.hint = textHint

        builder.setView(textInput)

        setCancelable(cancelable)

        negativeTitle?.let {
            setNegativeButton(it) { p0, p1 ->
                p0.dismiss()
                dismissListener()
            }
        }

        setPositiveButton(actionTitle) { p0, p1 ->
            p0.dismiss()
            actionListener(textInput.editText?.text.toString())
        }

        setOnCancelListener {
            it.dismiss()
            dismissListener()
        }
    }.create()

    builder.show().apply {
        getButton(DialogInterface.BUTTON_NEGATIVE)?.setTextColor(
            ContextCompat.getColor(
                context,
                R.color.dust
            )
        )
    }
}