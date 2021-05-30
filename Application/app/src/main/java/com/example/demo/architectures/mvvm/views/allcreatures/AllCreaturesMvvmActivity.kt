package com.example.demo.architectures.mvvm.views.allcreatures

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.R
import com.example.demo.architectures.mvvm.viewmodels.AllCreaturesViewModel
import com.example.demo.architectures.mvvm.views.creature.CreatureActivity
import com.example.demo.databinding.ActivityAllCreaturesMvvmBinding

class AllCreaturesMvvmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllCreaturesMvvmBinding

    private lateinit var viewModel: AllCreaturesViewModel

    private val adapter = CreatureAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllCreaturesMvvmBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)

        binding.contentAllCreaturesId.creaturesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contentAllCreaturesId.creaturesRecyclerView.adapter = adapter

        viewModel.getAllCreaturesLiveData().observe(this, Observer { creatures ->
            creatures?.let {
                adapter.updateCreatures(creatures)
            }
        })

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
                viewModel.clearAllCreatures()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}