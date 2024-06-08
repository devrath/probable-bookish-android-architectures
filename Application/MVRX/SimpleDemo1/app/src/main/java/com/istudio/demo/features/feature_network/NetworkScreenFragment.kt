package com.istudio.demo.features.feature_network

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.istudio.demo.R
import com.istudio.demo.common.Screen
import com.istudio.demo.common.ScreenProvider
import com.istudio.demo.databinding.FragmentScreenCounterBinding
import com.istudio.demo.databinding.FragmentScreenNetworkBinding
import com.istudio.demo.features.feature_counter.CounterScreenViewModel

class NetworkScreenFragment : Fragment(), ScreenProvider, MavericksView {

    override val screenName: String get() = Screen.NETWORK_FEATURE.pageName


    private var _binding: FragmentScreenNetworkBinding? = null
    private val binding get() = _binding!!


    private val viewModel: NetworkScreenViewModel by fragmentViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_screen_network, container, false)
        _binding = FragmentScreenNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListeners()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            println("$screenName <--> Current state: $state")
        }
    }


    private fun setOnClickListeners() {

    }


}