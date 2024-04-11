package com.istudio.demo.main

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
        setContentView(binding.root)
        // Set navigation controller
        navController = supportFragmentManager.findFragmentById(R.id.nav_controller)?.findNavController()
    }

    override fun invalidate() {

    }

}