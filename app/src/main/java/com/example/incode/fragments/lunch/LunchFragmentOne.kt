package com.example.incode.fragments.lunch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentLunchOneBinding

class LunchFragmentOne : Fragment() {
   private lateinit var bind: FragmentLunchOneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentLunchOneBinding.inflate(inflater)
        val action = LunchFragmentOneDirections.actionLunchFragmentOneToLunchFragmentThree()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)
        return bind.root
    }

}