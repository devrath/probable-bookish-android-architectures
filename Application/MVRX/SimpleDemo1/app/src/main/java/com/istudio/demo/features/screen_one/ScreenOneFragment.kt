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
        _binding = FragmentScreenOneBinding.inflate(inflater,container,false)
        binding.composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent { MyApplicationTheme { ScreenOneComposable(viewModel) } }
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun invalidate() {
        withState(viewModel) { state ->
            println("FRAGMENT <--> Current state: $state")
            println("FRAGMENT <--> CounterOneInitialValue state: ${state.counterOneInitialValue}")
            println("FRAGMENT <--> CounterTwoInitialValue state: ${state.counterTwoInitialValue}")
        }
    }



}