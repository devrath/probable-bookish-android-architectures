package com.example.demo.architectures.mvvm.views.avatars

import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.demo.architectures.commonlayer.model.Avatar
import com.example.demo.databinding.LayoutAvatarBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AvatarBottomDialogFragment : BottomSheetDialogFragment(), AvatarAdapter.AvatarListener {

  private lateinit var callback: AvatarAdapter.AvatarListener


  private var _binding: LayoutAvatarBottomSheetBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    _binding = LayoutAvatarBottomSheetBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    _binding?.avatarRecyclerView?.layoutManager = GridLayoutManager(context, 3)
    _binding?.avatarRecyclerView?.adapter = AvatarAdapter(AvatarStore.AVATARS, this)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    try {
      callback = activity as AvatarAdapter.AvatarListener
    } catch (e: ClassCastException) {
      throw ClassCastException(activity.toString() + " must implement AvatarAdapter.AvatarListener")
    }
  }

  override fun avatarClicked(avatar: Avatar) {
    callback.avatarClicked(avatar)
  }

  companion object {
    fun newInstance(): AvatarBottomDialogFragment {
      return AvatarBottomDialogFragment()
    }
  }
}