package com.example.demo.architectures.mvi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.architectures.mvp.view.allcreatures.AllCreaturesMvpActivity
import com.example.demo.databinding.ActivityMainMviBinding
import com.example.demo.databinding.ActivityMainMvpBinding

class MainActivityMvi : AppCompatActivity() {

    private lateinit var binding: ActivityMainMviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMviBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            startId.setOnClickListener {
                startActivity(Intent(this@MainActivityMvi, AllCreaturesMvpActivity::class.java))
            }
        }
    }

}