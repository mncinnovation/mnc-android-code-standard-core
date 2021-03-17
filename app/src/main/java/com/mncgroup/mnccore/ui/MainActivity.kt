package com.mncgroup.mnccore.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mncgroup.common.di.TAG_API
import com.mncgroup.core.util.ext.observeData
import com.mncgroup.core.util.ext.toVisibleOrGone
import com.mncgroup.mnccore.R
import com.mncgroup.mnccore.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private var _currentNavController = MutableLiveData<NavController>()
    private val currentNavController: LiveData<NavController> get() = _currentNavController

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpBottomNavBar()
    }

    private fun setUpBottomNavBar() {
        val navController = findNavController(R.id.nav_host_fragment)

        viewModel.userData.observeData(this) { userData ->
            binding.navView.menu.clear()
            userData?.let {
                if (it.isNotEmpty()) {
                    binding.navView.inflateMenu(R.menu.menu_main_bottom_nav)
                } else {
                    binding.navView.inflateMenu(R.menu.menu_notloggedin_bottom_nav)
                }
            }
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard,
                R.id.navigation_notifications,
                R.id.navigation_auth
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
        _currentNavController.value = navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            setBottomNavigationVisibility(
                (destination.id == R.id.navigation_home ||
                        destination.id == R.id.navigation_dashboard ||
                        destination.id == R.id.navigation_notifications ||
                        destination.id == R.id.loginFragment).toVisibleOrGone()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.e(TAG_API, "naivgateUp : ${currentNavController.value?.navigateUp()}")
        return currentNavController.value?.navigateUp() ?: false
    }

    private fun setBottomNavigationVisibility(visibility: Int) {
        // get the reference of the bottomNavigationView and set the visibility.
        binding.navView.visibility = visibility
    }
}