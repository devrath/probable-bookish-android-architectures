package com.example.demo.architectures.mvp.contracts

import androidx.annotation.DrawableRes
import com.example.demo.architectures.commonlayer.model.AttributeType

interface CreatureContract {

  interface Presenter {
    fun updateName(name: String)
    fun attributeSelected(attributeType: AttributeType, position: Int)
    fun drawableSelected(drawable: Int)
    fun isDrawableSelected(): Boolean
    fun saveCreature()
  }

  interface View {
    fun showHitPoints(hitPoints: String)
    fun showAvatarDrawable(@DrawableRes resourceId: Int)
    fun showCreatureSaved()
    fun showCreatureSaveError()
  }

}