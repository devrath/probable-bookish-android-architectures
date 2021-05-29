package com.example.demo.mvp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityMainMvpBinding

class MainActivityMvp : AppCompatActivity() {

    private lateinit var binding: ActivityMainMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMvpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}