package com.example.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.mvi.activities.MainActivityMvi
import com.example.demo.mvp.activities.MainActivityMvp
import com.example.demo.mvvm.activities.MainActivityMvvm

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
                startActivity(Intent(this@MainActivity, MainActivityMvvm::class.java))
            }
            mvpDemoId.setOnClickListener {
                // MVP
                startActivity(Intent(this@MainActivity, MainActivityMvp::class.java))
            }
            mviDemoId.setOnClickListener {
                // MVI
                startActivity(Intent(this@MainActivity, MainActivityMvi::class.java))
            }
        }
    }


}