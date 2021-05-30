package com.example.demo.architectures.mvvm.views.allcreatures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityAllCreaturesMvvmBinding

class AllCreaturesMvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllCreaturesMvvmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCreaturesMvvmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
    }

}