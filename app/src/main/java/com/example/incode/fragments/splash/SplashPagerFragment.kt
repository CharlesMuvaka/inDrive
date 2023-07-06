package com.example.incode.fragments.splash

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

class SplashPagerFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplashPagerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashPagerBinding.inflate(inflater)

        val action = SplashPagerFragmentDirections.actionSplashPagerFragmentToSplashFragment2()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        bind.buttonSkip.setOnClickListener(this::onClick)
        bind.buttonNext.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(v: View?) {
        if(v == bind.buttonSkip){
            val action = SplashPagerFragmentDirections.actionSplashPagerFragmentToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        if (v == bind.buttonNext){
            val action = SplashPagerFragmentDirections.actionSplashPagerFragmentToSplashFragment2()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}