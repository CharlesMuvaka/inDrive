package com.example.incode.fragments.church

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentWorshipThreeBinding

class WorshipFragmentThree : Fragment() {
    private lateinit var bind: FragmentWorshipThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentWorshipThreeBinding.inflate(inflater)
        val action = WorshipFragmentThreeDirections.actionWorshipFragmentThreeToWorshipFragmentOne()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)
        return bind.root
    }

}