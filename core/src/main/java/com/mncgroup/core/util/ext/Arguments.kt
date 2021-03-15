@file:Suppress("UNCHECKED_CAST", "unused")

package com.mncgroup.core.util.ext

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * Start an Activity for given class T and allow to work on intent with "run" lambda function
 */
fun <T : Fragment> T.withSerializable(vararg arguments: Pair<String, Serializable>): T {
    val bundle = this.arguments ?: Bundle()
    arguments.forEach { bundle.putSerializable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Start an Activity for given class T and allow to work on intent with "run" lambda function
 */
fun <T : Fragment> T.withParcelable(vararg arguments: Pair<String, Parcelable>): T {
    val bundle = this.arguments ?: Bundle()
    arguments.forEach { bundle.putParcelable(it.first, it.second) }
    this.arguments = bundle
    return this
}

/**
 * Retrieve property from intent
 */
fun <T : Any> AppCompatActivity.argument(key: String) = lazy {
    intent.extras?.get(key) as T
}

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> AppCompatActivity.argument(key: String, defaultValue: T? = null) = lazy {
    intent.extras?.get(key) as? T ?: defaultValue
}

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> AppCompatActivity.argumentNotNull(key: String, defaultValue: T) = lazy {
    intent.extras?.get(key) as? T ?: defaultValue
}

/**
 * Retrieve property from intent
 */
fun <T : Any> Fragment.argument(key: String) = lazy { arguments?.get(key) as T }

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.argument(key: String, defaultValue: T? = null) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}

/**
 * Retrieve property with default value from intent
 */
fun <T : Any> Fragment.argumentNotNull(key: String, defaultValue: T) = lazy {
    arguments?.get(key)  as? T ?: defaultValue
}