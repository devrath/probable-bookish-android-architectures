package com.istudio.demo.features.screen_one

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.withState
import com.istudio.demo.R
import com.istudio.demo.common.Screen
import com.istudio.demo.common.ScreenProvider
import com.istudio.demo.databinding.FragmentScreenOneBinding
import com.istudio.demo.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScreenOneFragment : Fragment(), ScreenProvider, MavericksView {

    override val screenName: String get() = Screen.SCREEN_ONE.pageName


    private var _binding: FragmentScreenOneBinding? = null
    private val binding get() = _binding!!


    private val viewModel: ScreenOneViewModel by fragmentViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.fragment_screen_one, container, false)
        _binding = FragmentScreenOneBinding.inflate(inflater, container, false)
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
            println("$screenName <--> CounterOneInitialValue state: ${state.counterOneInitialValue}")
            println("$screenName <--> CounterTwoInitialValue state: ${state.counterTwoInitialValue}")

            binding.counterOneTextId.text = state.counterOneInitialValue.toString()
            binding.counterTwoTextId.text = state.counterTwoInitialValue.toString()
        }
    }


    private fun setOnClickListeners() {
        binding.apply {
            counterOneButtonId.setOnClickListener {
                viewModel.incrementCounterOne()
            }
            counterTwoButtonId.setOnClickListener {
                viewModel.incrementCounterTwo()
            }
        }
    }


}