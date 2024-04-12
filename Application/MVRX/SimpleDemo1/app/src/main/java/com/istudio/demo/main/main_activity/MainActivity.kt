package com.istudio.demo.main.main_activity

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.airbnb.mvrx.InternalMavericksApi
import com.airbnb.mvrx.MavericksView
import com.istudio.demo.R
import com.istudio.demo.common.mavericks.activityViewModel
import com.istudio.demo.databinding.ActivityMainBinding
import com.istudio.demo.utils.isTablet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),MavericksView {

    val TAG = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding

    private var navController : NavController? = null

    @OptIn(InternalMavericksApi::class)
    private val viewModel : MainViewModel by activityViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        validateIfPortrait()
        setContentView(binding.root)
        // Set navigation controller
        navController = supportFragmentManager.findFragmentById(R.id.nav_controller)?.findNavController()
    }

    @SuppressLint("SourceLockedOrientationActivity")
    private fun validateIfPortrait() {
        if (!isTablet) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    override fun invalidate() {

    }

}