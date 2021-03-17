package com.mncgroup.core.util.ext

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observeData(owner: LifecycleOwner, crossinline observer: (T?) -> Unit) =
        observe(owner, Observer { observer(it) })