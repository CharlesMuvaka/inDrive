package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.incode.R
import com.example.incode.databinding.FragmentSplashPagerBinding
import com.example.incode.models.SplashData

class SplashPagerFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentSplashPagerBinding
    private var splashObject: SplashData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            splashObject = it.getSerializable("splash") as SplashData
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentSplashPagerBinding.inflate(inflater)

        splashObject!!.image?.let { bind.image.setImageResource(it) }
        bind.pageName.text = splashObject!!.title
        bind.pageDescription.text = splashObject!!.description

        bind.buttonSkip.setOnClickListener(this::onClick)
        bind.buttonNext.setOnClickListener(this::onClick)

        return bind.root
    }

    companion object {
        @JvmStatic
        fun newInstance(splash: SplashData) = SplashPagerFragment().apply {
            arguments = Bundle().apply {
                putSerializable("splash", splash)
            }
        }
    }

    override fun onClick(v: View?) {
        if(v == bind.buttonSkip){
            v.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }
}