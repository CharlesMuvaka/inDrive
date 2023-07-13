package com.example.incode.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.incode.R
import com.example.incode.databinding.FragmentSplashBinding

class SplashFragment : Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting the status bar color
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)



    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentSplashBinding.inflate(inflater)

        bind.root.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.enter_anim)
        )
        val action = SplashFragmentDirections.actionSplashFragmentToDrawerFragment()
        Handler(Looper.getMainLooper()).postDelayed({
            NavHostFragment.findNavController(this).navigate(action)
        }, 5000)

        //setting the click listeners
        bind.buttonSkip.setOnClickListener(this::onClick)
        bind.buttonNext.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.buttonSkip){
            val action = SplashFragmentDirections.actionSplashFragmentToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        if (p0 == bind.buttonNext){
            val action = SplashFragmentDirections.actionSplashFragmentToDrawerFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }
    }
}