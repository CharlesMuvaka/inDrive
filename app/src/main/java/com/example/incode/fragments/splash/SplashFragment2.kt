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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment2_to_splashFragment)
        }, 5000)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplash2Binding.inflate(inflater)

        //setting the click listeners
        bind.buttonNext.setOnClickListener(this::onClick)
        bind.buttonSkip.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.buttonNext){
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment2_to_splashFragment)
        }
        if (p0 == bind.buttonSkip){
            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment2_to_loginFragment)

        }
    }

}