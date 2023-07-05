package com.example.incode.fragments.evening

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentEveningTwoBinding


class EveningFragmentTwo : Fragment() {
   private lateinit var bind: FragmentEveningTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentEveningTwoBinding.inflate(inflater)
        val action = EveningFragmentTwoDirections.actionEveningFragmentTwoToEveningFragmentOne()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)
        return bind.root
    }

}