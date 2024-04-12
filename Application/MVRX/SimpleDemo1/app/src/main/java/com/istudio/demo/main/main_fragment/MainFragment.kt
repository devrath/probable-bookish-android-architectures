package com.istudio.demo.main.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.airbnb.mvrx.MavericksView
import com.istudio.demo.R
import com.istudio.demo.common.Screen
import com.istudio.demo.common.ScreenProvider
import com.istudio.demo.common.navigationIdsUsesNavbar
import com.istudio.demo.databinding.FragmentMainBinding
import com.istudio.demo.main.main_fragment.tabs.TabViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.airbnb.mvrx.activityViewModel
import com.google.android.material.navigation.NavigationBarView

@AndroidEntryPoint
class MainFragment : Fragment(), MavericksView, ScreenProvider {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    //private val mainViewModel : MainViewModel by activityViewModels()
    private val tabViewModel : TabViewModel by activityViewModel()

    override val screenName: String
        get() = Screen.MAIN.pageName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBottomNavigation()
        addTabListener()
    }

    override fun onDestroyView() {
        onBackPressedCallback.remove()
        super.onDestroyView()
        _binding = null
    }


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            handleBackPress()
        }
    }

    private fun handleBackPress() {
        activity?.finish()
    }

    private fun setUpBottomNavigation() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        /*val navigationPackageIdentifier = "${requireActivity().application.packageName}:id/"
        navController.addOnDestinationChangedListener { _ , destination , _  ->
            val showNavBar = navigationIdsUsesNavbar.contains(
                destination.displayName.removePrefix(navigationPackageIdentifier)
            )
        }*/

    }

    private fun addTabListener() {
       val bottomView : NavigationBarView = binding.bottomNav
       bottomView.apply {
           setOnItemReselectedListener { item ->
               tabViewModel.setTabReselected(item.title.toString())
           }
       }
    }

    override fun invalidate() {

    }

}