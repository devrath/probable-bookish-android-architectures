package com.istudio.demo.main.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.istudio.demo.R
import com.istudio.demo.common.Screen
import com.istudio.demo.common.ScreenProvider
import com.istudio.demo.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(), ScreenProvider {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel : MainViewModel by activityViewModels()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply { arguments = Bundle().apply {} }
    }

}