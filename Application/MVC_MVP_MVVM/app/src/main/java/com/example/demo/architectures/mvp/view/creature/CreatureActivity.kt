package com.example.demo.architectures.mvp.view.creature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.DrawableRes
import com.example.demo.R
import com.example.demo.databinding.ActivityCreatureBinding
import com.example.demo.architectures.commonlayer.model.AttributeStore
import com.example.demo.architectures.commonlayer.model.AttributeType
import com.example.demo.architectures.commonlayer.model.AttributeValue
import com.example.demo.architectures.commonlayer.model.Avatar
import com.example.demo.architectures.mvp.contracts.CreatureContract
import com.example.demo.architectures.mvp.presenter.CreaturePresenter
import com.example.demo.architectures.mvp.view.avatars.AvatarAdapter
import com.example.demo.architectures.mvp.view.avatars.AvatarBottomDialogFragment


class CreatureActivity : AppCompatActivity(), AvatarAdapter.AvatarListener, CreatureContract.View {

  private lateinit var binding: ActivityCreatureBinding
  private val presenter = CreaturePresenter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCreatureBinding.inflate(layoutInflater)
    setContentView(binding.root)

    presenter.setView(this)

    configureUI()
    configureSpinnerAdapters()
    configureSpinnerListeners()
    configureEditText()
    configureClickListeners()
  }

  private fun configureUI() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    title = getString(R.string.add_creature)
    if (presenter.isDrawableSelected()) hideTapLabel()
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
        presenter.attributeSelected(AttributeType.INTELLIGENCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.attributeSelected(AttributeType.STRENGTH, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    binding.endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
      override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.attributeSelected(AttributeType.ENDURANCE, position)
      }
      override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
  }

  private fun configureEditText() {
    binding.nameEditText.addTextChangedListener(object : TextWatcher {
      override fun afterTextChanged(s: Editable?) {}
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.updateName(s.toString())
      }
    })
  }

  private fun configureClickListeners() {
    binding.avatarImageView.setOnClickListener {
      val bottomDialogFragment = AvatarBottomDialogFragment.newInstance()
      bottomDialogFragment.show(supportFragmentManager, "AvatarBottomDialogFragment")
    }

    binding.saveButton.setOnClickListener {
      presenter.saveCreature()
    }
  }

  override fun avatarClicked(avatar: Avatar) {
    presenter.drawableSelected(avatar.drawable)
    hideTapLabel()
  }

  private fun hideTapLabel() {
    binding.tapLabel.visibility = View.INVISIBLE
  }

  override fun showHitPoints(hitPoints: String) {
    binding.hitPoints.text = hitPoints
  }

  override fun showAvatarDrawable(@DrawableRes resourceId: Int) {
    binding.avatarImageView.setImageResource(resourceId)
  }

  override fun showCreatureSaved() {
    Toast.makeText(this, getString(R.string.creature_saved), Toast.LENGTH_SHORT).show()
    finish()
  }

  override fun showCreatureSaveError() {
    Toast.makeText(this, getString(R.string.error_saving_creature), Toast.LENGTH_SHORT).show()
  }

}
