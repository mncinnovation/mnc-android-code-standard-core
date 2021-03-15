package com.mncgroup.core.ui

import androidx.annotation.DrawableRes

interface ToolbarHelper {
    fun showToolbarBack(show: Boolean)
    fun showToolbar(show: Boolean)
    fun setToolbarTitle(title: String)
    fun setToolbarTitle(titleRes: Int)
    fun showToolbarTitle(show: Boolean)
    fun setUseLogo(@DrawableRes resId: Int)
    fun hideLogo()
}