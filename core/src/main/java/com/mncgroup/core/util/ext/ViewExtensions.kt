package com.mncgroup.core.util.ext

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.forEach

/**
 * to set density format (dp) of integer value
 * @param value value to be converted to dip
 */
fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

/**
 * An Boolean extention to get invisibility of view
 *
 * @return Int: View.VISIBLE if true and View.INVISIBLE if false
 */
fun Boolean?.toVisibleOrInvisible(): Int {
    if (this == true) {
        return View.VISIBLE
    }
    return View.INVISIBLE
}

/**
 * An Boolean extention to get visibility or gone of view
 *
 * @return Int : View.VISIBLE if true and View.GONE if false
 */
fun Boolean?.toVisibleOrGone(): Int {
    if (this == true) {
        return View.VISIBLE
    }
    return View.GONE
}

fun View.toBitmap(): Bitmap {
    val returnedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    val bgDrawable = background
    if (bgDrawable != null)
        bgDrawable.draw(canvas)
    else
        canvas.drawColor(Color.WHITE)
    draw(canvas)
    return returnedBitmap
}

fun View.setApplyWindowInsetsToChild() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        (v as? ViewGroup)?.forEach { ViewCompat.dispatchApplyWindowInsets(it, insets) }
        insets
    }
}

fun View.showKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        hideSoftInputFromWindow(windowToken, 0)
    }
}