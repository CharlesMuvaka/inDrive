package com.example.incode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.incode.R
import com.example.incode.databinding.FragmentDriveThereBinding
import com.example.incode.models.PlaceResult

class DriveThereFragment : Fragment(), View.OnClickListener {
    private lateinit var bind:FragmentDriveThereBinding
    private var place: PlaceResult? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            place = it.getSerializable("place") as PlaceResult
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentDriveThereBinding.inflate(inflater)

        bind.wallet.setOnClickListener(this::onClick)
        bind.back.setOnClickListener(this::onClick)
        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: PlaceResult) =
            DriveThereFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("place", param1)
                }
            }
    }

    override fun onClick(v: View?) {
        if(v == bind.wallet){
            val intent = requireContext().packageManager.getLaunchIntentForPackage("com.android.stk")
            if (intent != null){
                requireContext().startActivity(intent)
            }
        }

        if(v == bind.back){
            requireActivity().finish()
        }
    }
}