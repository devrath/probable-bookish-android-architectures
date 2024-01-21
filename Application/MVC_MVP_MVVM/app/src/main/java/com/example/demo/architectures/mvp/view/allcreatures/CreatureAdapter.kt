package com.example.demo.architectures.mvp.view.allcreatures

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.demo.R
import com.example.demo.extensions.inflate
import com.example.demo.architectures.commonlayer.model.Creature
import com.example.demo.databinding.ListItemCreatureBinding

class CreatureAdapter(private val creatures: MutableList<Creature>)
  : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemBinding = ListItemCreatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(itemBinding)
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(creatures[position])
  }

  override fun getItemCount() = creatures.size

  fun updateCreatures(creatures: List<Creature>) {
    this.creatures.clear()
    this.creatures.addAll(creatures)
    notifyDataSetChanged()
  }

  class ViewHolder(private val itemBinding: ListItemCreatureBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    private lateinit var creature: Creature

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun bind(creature: Creature) {
      this.creature = creature
      itemBinding.avatarListItem.setImageDrawable(itemView.context.getDrawable(creature.drawable))
      itemBinding.name.text = creature.name
      itemBinding.hitPoints.text = creature.hitPoints.toString()
    }
  }
}