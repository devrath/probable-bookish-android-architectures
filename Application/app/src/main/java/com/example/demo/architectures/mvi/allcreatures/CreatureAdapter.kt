package com.example.demo.architectures.mvi.allcreatures

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.example.demo.architectures.mvi.data.model.Creature
import com.example.demo.databinding.ListItemCreatureBinding

class CreatureAdapter(private val creatures: MutableList<Creature>)
  : RecyclerView.Adapter<CreatureAdapter.ViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemBinding = ListItemCreatureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ViewHolder(itemBinding)
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val creature: Creature = creatures[position]
    holder.bind(creature)
  }

  override fun getItemCount() = creatures.size

  fun updateCreatures(creatures: List<Creature>) {
    this.creatures.clear()
    this.creatures.addAll(creatures)
    notifyDataSetChanged()
  }

  class ViewHolder(private val itemBinding: ListItemCreatureBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    fun bind(creature: Creature) {
      itemBinding.avatarListItem.setImageDrawable(itemView.context.getDrawable(creature.drawable))
      itemBinding.name.text = creature.name
      itemBinding.hitPoints.text = creature.hitPoints.toString()
    }
  }
}