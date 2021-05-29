package com.example.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setCLickListeners()
    }

    private fun setCLickListeners() {
        binding.apply {
            mvvmDemoId.setOnClickListener {
                // MVVM
            }
            mvpDemoId.setOnClickListener {
                // MVP
            }
            mviDemoId.setOnClickListener {
                // MVI
            }
        }
    }


}