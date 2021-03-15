package com.mncgroup.core.util.ext

import android.content.Context
import android.view.View

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