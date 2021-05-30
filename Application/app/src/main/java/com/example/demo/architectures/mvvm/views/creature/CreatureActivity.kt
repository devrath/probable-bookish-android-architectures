package com.example.demo.architectures.mvvm.views.creature

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.demo.R
import com.example.demo.architectures.commonlayer.model.AttributeStore
import com.example.demo.architectures.commonlayer.model.AttributeType
import com.example.demo.architectures.commonlayer.model.AttributeValue
import com.example.demo.architectures.commonlayer.model.Avatar
import com.example.demo.architectures.mvvm.viewmodels.CreatureViewModel
import com.example.demo.architectures.mvvm.views.avatars.AvatarAdapter
import com.example.demo.architectures.mvvm.views.avatars.AvatarBottomDialogFragment
import com.example.demo.databinding.ActivityCreatureBinding


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener {

  private lateinit var viewModel: CreatureViewModel

  private lateinit var binding: ActivityCreatureBinding


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCreatureBinding.inflate(layoutInflater)
    setContentView(binding.root)

    viewModel = ViewModelProviders.of(this).get(CreatureViewModel::class.java)

    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureEditText()
    configureClickListeners()
    configureLiveDataObservers()
  }
  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)
    if (viewModel.drawable != 0) hideTapLabel()
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
        viewModel.attributeSelected(AttributeType.INTELLIGENCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.STRENGTH, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.attributeSelected(AttributeType.ENDURANCE, position)

      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureEditText() {
    binding.nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        viewModel.name = s.toString()
      }
    })
  }

  private fun configureClickListeners() {
    binding.avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    binding.saveButton.setOnClickListener {
      if (viewModel.saveCreature()) {
        Toast.makeText(this, getString(R.string.creature_saved), Toast.LENGTH_SHORT).show()
        finish()
      } else {
        Toast.makeText(this, getString(R.string.error_saving_creature), Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun configureLiveDataObservers() {
    viewModel.getCreatureLiveData().observe(this, Observer { creature ->
      creature?.let {
        binding.hitPoints.text = creature.hitPoints.toString()
        binding.avatarImageView.setImageResource(creature.drawable)
        binding.nameEditText.setText(creature.name)
      }
    })
  }

  override fun avatarClicked(avatar: Avatar) {
    viewModel.drawableSelected(avatar.drawable)
    hideTapLabel()
  }

  private fun hideTapLabel() {
    binding.tapLabel.visibility = View.INVISIBLE
  }
}
