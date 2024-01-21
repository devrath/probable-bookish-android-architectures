package com.example.demo.architectures.mvp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityMainMvpBinding
import com.example.demo.architectures.mvp.view.allcreatures.AllCreaturesMvpActivity

class MainActivityMvp : AppCompatActivity() {

    private lateinit var binding: ActivityMainMvpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMvpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {
            startId.setOnClickListener {
                startActivity(Intent(this@MainActivityMvp, AllCreaturesMvpActivity::class.java))
            }
        }
    }

}