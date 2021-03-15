package com.mncgroup.core.ui

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.mncgroup.core.util.ext.indonesianLocale
import com.mncgroup.core.util.ext.updateBaseContextLocale

abstract class BaseActivity : AppCompatActivity(), ToolbarHelper {

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.updateBaseContextLocale(indonesianLocale))
    }

    override fun showToolbarTitle(show: Boolean) {
        supportActionBar?.setDisplayShowTitleEnabled(show)
    }

    override fun setToolbarTitle(titleRes: Int) {
        supportActionBar?.setTitle(titleRes)
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun showToolbar(show: Boolean) {
        if (show) {
            supportActionBar?.show()
        } else {
            supportActionBar?.hide()
        }
    }

    override fun showToolbarBack(show: Boolean) {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(show)
        }
    }

    override fun hideLogo() {
        supportActionBar?.apply {
            setDisplayUseLogoEnabled(false)
        }
    }

    override fun setUseLogo(@DrawableRes resId: Int) {
        supportActionBar?.apply {
            setDisplayUseLogoEnabled(true)
            setDisplayShowTitleEnabled(false)
            setIcon(resId)
        }
    }
}