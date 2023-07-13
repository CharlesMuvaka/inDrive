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
import com.example.incode.databinding.FragmentSplashMainBinding
import com.example.incode.databinding.FragmentSplashPagerBinding

class SplashMainFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplashMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashMainBinding.inflate(inflater)

        val action = SplashMainFragmentDirections.actionSplashMainFragmentToDrawerFragment()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 8500)

        //setting the click listeners
        bind.buttonNext.setOnClickListener(this::onClick)
        bind.buttonSkip.setOnClickListener(this::onClick)


        return bind.root
    }

    override fun onClick(v: View?) {
        if(v == bind.buttonSkip){
            val action = SplashMainFragmentDirections.actionSplashMainFragmentToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        if (v == bind.buttonNext){
            val action = SplashMainFragmentDirections.actionSplashMainFragmentToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}