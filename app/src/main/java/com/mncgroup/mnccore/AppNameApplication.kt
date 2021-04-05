//TODO: rename main package name with your application package name
package com.mncgroup.mnccore

import android.app.Application
import com.mncgroup.core.util.InternetUtil

/**
 * An application class of project
 *
 * TODO: Rename with project name application, for ex : MetubeApplication
 */
class AppNameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //init internet util
        InternetUtil.init(this)
    }
}