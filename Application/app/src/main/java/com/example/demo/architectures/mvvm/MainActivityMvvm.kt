package com.example.demo.architectures.mvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityMainMvvmBinding

class MainActivityMvvm : AppCompatActivity(){

    private lateinit var binding: ActivityMainMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMvvmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}