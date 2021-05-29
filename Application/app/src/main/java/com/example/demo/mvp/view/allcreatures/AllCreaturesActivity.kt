package com.example.demo.mvp.view.allcreatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.demo.R
import com.example.demo.databinding.ActivityAllCreaturesBinding
import com.example.demo.mvp.view.creature.CreatureActivity

class AllCreaturesActivity : AppCompatActivity() {

  private lateinit var binding: ActivityAllCreaturesBinding

  private val adapter = CreatureAdapter(mutableListOf())

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAllCreaturesBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    setSupportActionBar(binding.toolbar)

    binding.contentAllCreaturesId.creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
    binding.contentAllCreaturesId.creaturesRecyclerView.adapter = adapter

    binding.fab.setOnClickListener {
      startActivity(Intent(this, CreatureActivity::class.java))
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.action_clear_all -> {
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
