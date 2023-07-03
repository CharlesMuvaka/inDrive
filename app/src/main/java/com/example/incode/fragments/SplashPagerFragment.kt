package com.example.incode.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentSplashPagerBinding
import com.example.incode.models.SplashData

class SplashPagerFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplashPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_splashPagerFragment_to_splashFragment2)
        }, 5000)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashPagerBinding.inflate(inflater)

        bind.buttonSkip.setOnClickListener(this::onClick)
        bind.buttonNext.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(v: View?) {
        if(v == bind.buttonSkip){
            v.findNavController().navigate(R.id.action_splashPagerFragment_to_loginFragment)
        }

        if (v == bind.buttonNext){
            v.findNavController().navigate(R.id.action_splashPagerFragment_to_splashFragment2)
        }
    }
}