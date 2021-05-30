package com.example.demo.architectures.mvp.view.allcreatures

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.demo.R
import com.example.demo.databinding.ActivityAllCreaturesBinding
import com.example.demo.architectures.mvp.contracts.AllCreaturesContract
import com.example.demo.architectures.mvp.presenter.AllCreaturesPresenter
import com.example.demo.architectures.mvp.view.creature.CreatureActivity

class AllCreaturesActivity : AppCompatActivity(), AllCreaturesContract.View {

  private lateinit var binding: ActivityAllCreaturesBinding

  private val adapter = CreatureAdapter(mutableListOf())

  private val presenter = AllCreaturesPresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityAllCreaturesBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)
    setSupportActionBar(binding.toolbar)

    presenter.setView(this)

    presenter.getAllCreatures().observe(this, { creatures ->
      creatures?.let {
        adapter.updateCreatures(creatures)
      }
    })

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
        presenter.clearAllCreatures()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun showCreaturesCleared() {
    Toast.makeText(this, getString(R.string.creatures_cleared), Toast.LENGTH_SHORT).show()
  }
}
