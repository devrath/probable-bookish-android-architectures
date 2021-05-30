package com.example.demo.architectures.mvi.data.model

data class AttributeValue(val name: String = "", val value: Int = 0) {
  override fun toString() = "$name: $value"
}