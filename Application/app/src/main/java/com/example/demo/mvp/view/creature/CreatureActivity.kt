package com.example.demo.mvp.view.creature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.demo.R
import com.example.demo.databinding.ActivityCreatureBinding
import com.example.demo.mvp.model.AttributeStore
import com.example.demo.mvp.model.AttributeValue
import com.example.demo.mvp.model.Avatar
import com.example.demo.mvp.view.avatars.AvatarAdapter
import com.example.demo.mvp.view.avatars.AvatarBottomDialogFragment


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

  private lateinit var binding: ActivityCreatureBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCreatureBinding.inflate(layoutInflater)
    val view = binding.root

    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureEditText()
    configureClickListeners()
  }

  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)
    // TODO: hide label
  }

  private fun configureSpinnerAdapters() {
    binding.intelligence.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
    binding.strength.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
    binding.endurance.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
  }

  private fun configureSpinnerListeners() {
    binding.intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureEditText() {
    binding.nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // TODO: handle text changed
      }
    })
  }

  private fun configureClickListeners() {
    binding.avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    binding.saveButton.setOnClickListener {
      // TODO: handle save button clicked
    }
  }

  override fun avatarClicked(avatar: Avatar) {
    // TODO: handle avatar clicked
    hideTapLabel()
  }

  private fun hideTapLabel() {
    binding.tapLabel.visibility = View.INVISIBLE
  }
}
