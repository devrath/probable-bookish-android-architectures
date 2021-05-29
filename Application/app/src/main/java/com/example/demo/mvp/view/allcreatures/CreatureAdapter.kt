package com.example.demo.mvp.view.allcreatures

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.demo.R
import com.example.demo.extensions.inflate
import com.example.demo.mvp.model.Creature

class CreatureAdapter(private val creatures: MutableList<Creature>)
  : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(parent.inflate(R.layout.list_item_creature))
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(creatures[position])
  }

  override fun getItemCount() = creatures.size

  fun updateCreatures(creatures: List<Creature>) {
    this.creatures.clear()
    this.creatures.addAll(creatures)
    notifyDataSetChanged()
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var creature: Creature

    fun bind(creature: Creature) {
      this.creature = creature
      // TODO: populate views
    }
  }
}