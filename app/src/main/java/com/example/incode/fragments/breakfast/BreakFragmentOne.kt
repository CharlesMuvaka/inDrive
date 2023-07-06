package com.example.incode.fragments.breakfast

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.SecondActivity
import com.example.incode.databinding.FragmentBreakOneBinding

class BreakFragmentOne : Fragment() {
    private lateinit var bind: FragmentBreakOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentBreakOneBinding.inflate(inflater)
        val action = BreakFragmentOneDirections.actionBreakFragmentOneToBreakFragmentThree()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }

        return bind.root
    }

}