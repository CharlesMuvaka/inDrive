package com.example.incode.fragments.afternoon

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
import com.example.incode.databinding.FragmentNoonOneBinding

class NoonFragmentOne : Fragment() {
    private lateinit var bind: FragmentNoonOneBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bind = FragmentNoonOneBinding.inflate(inflater)

        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_noonFragmentOne_to_noonFragmentThree)
        }, 5000)
        bind.root.setOnClickListener{
            val intent = Intent(requireContext(), SecondActivity::class.java)
            startActivity(intent)
        }
        return bind.root
    }

}