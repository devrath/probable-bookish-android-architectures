package com.example.demo.architectures.mvi.addcreature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.demo.R
import com.example.demo.architectures.mvi.data.model.AttributeStore
import com.example.demo.architectures.mvi.data.model.AttributeValue
import com.example.demo.architectures.mvi.data.model.Avatar
import com.example.demo.architectures.mvi.addcreature.avatars.AvatarAdapter
import com.example.demo.architectures.mvi.addcreature.avatars.AvatarBottomDialogFragment
import kotlinx.android.synthetic.main.activity_creature.*


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_creature)

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
    intelligence.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE)
    strength.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.STRENGTH)
    endurance.adapter = ArrayAdapter<AttributeValue>(this,
        android.R.layout.simple_spinner_dropdown_item, AttributeStore.ENDURANCE)
  }

  private fun configureSpinnerListeners() {
    intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // TODO: handle selection
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureEditText() {
    nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // TODO: handle text changed
      }
    })
  }

  private fun configureClickListeners() {
    avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    saveButton.setOnClickListener {
      // TODO: handle save button clicked
    }
  }

  override fun avatarClicked(avatar: Avatar) {
    // TODO: handle avatar clicked
    hideTapLabel()
  }

  private fun hideTapLabel() {
    tapLabel.visibility = View.INVISIBLE
  }
}
