package com.example.demo.architectures.mvi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityMainMviBinding

class MainActivityMvi : AppCompatActivity() {

    private lateinit var binding: ActivityMainMviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMviBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}