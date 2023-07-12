package com.example.incode.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.incode.R
import com.example.incode.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var bind: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentRegisterBinding.inflate(inflater)

        return bind.root
    }

}