package com.example.incode.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentSplash2Binding

class SplashFragment2: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplash2Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplash2Binding.inflate(inflater)
        val action = SplashFragment2Directions.actionSplashFragment2ToSplashFragment()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)


        //setting the click listeners
        bind.buttonNext.setOnClickListener(this::onClick)
        bind.buttonSkip.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.buttonNext){
            val action = SplashFragment2Directions.actionSplashFragment2ToSplashFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
        if (p0 == bind.buttonSkip){
            val action = SplashFragment2Directions.actionSplashFragment2ToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)

        }
    }

}