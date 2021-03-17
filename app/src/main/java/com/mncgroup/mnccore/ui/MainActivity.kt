package com.mncgroup.mnccore.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
//import androidx.navigation.ui.setupActionBarWithNavController
import com.mncgroup.mnccore.R
import com.mncgroup.mnccore.databinding.ActivityMainBinding
import com.mncgroup.mnccore.util.setupWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    //another way to setup bottom nav bar with only one navigation
//    private fun setUpBottomNavBar(){
//        val navController = findNavController(R.id.nav_host_fragment)
//
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_auth
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        binding.navView.setupWithNavController(navController)
//    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        binding.navView.apply {
            menu.clear() //clear old inflated items.
            //if not logged in
            inflateMenu(R.menu.menu_notloggedin_bottom_nav)
            //if logged in
//            inflateMenu(R.menu.menu_main_bottom_nav)
        }

        //if not logged in
        var navGraphIds = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_auth
        )

        //if logged in
//        navGraphIds = listOf(
//            R.navigation.navigation_home,
//            R.navigation.navigation_dashboard,
//            R.navigation.navigation_notifications
//        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = binding.navView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fcv_main,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}