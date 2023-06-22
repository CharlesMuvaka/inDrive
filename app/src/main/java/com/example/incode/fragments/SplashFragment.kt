package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.incode.R
import com.example.incode.adapters.ViewPagerAdapter
import com.example.incode.databinding.FragmentSplashBinding
import com.example.incode.models.SplashData

class SplashFragment: Fragment() {
    private lateinit var bind: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setting the status bar color
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.white)

        //initialising the splash
//        Handler(Looper.getMainLooper()).postDelayed({
//            NavHostFragment.findNavController(this).navigate(R.id.action_splashFragment_to_loginFragment)
//        }, 3000)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        bind = FragmentSplashBinding.inflate(inflater)

        //creating a list to store the splash objects
        val list = ArrayList<SplashData>()
        val fragmentList = ArrayList<Fragment>()

        //creating the splash objects
        val splashObjectOne = SplashData("Your ideal tour Experience").apply {
            description = "Don’t be a stranded and bored tourist. We have all the places you need to have a satisfactory experience."
            image = R.drawable.hike
        }
        list.add(splashObjectOne)
        val splashObjectTwo = SplashData("The only driver you can trust").apply{
            description = "In a hurry to get to your destination, dont worry we got you"
            image = R.drawable.gym
        }
        list.add(splashObjectTwo)

        val splashObjectThree = SplashData("Choose your Ideal route").apply {
            description = "Select a route to your destination  you think suits you."
            image = R.drawable.download
        }
        list.add(splashObjectThree)

        for (i in list.indices){
            val fragment = SplashPagerFragment.newInstance(list[i])
            fragmentList.add(fragment)
        }

        //defining the pager adapter to bid data to the view pager
        val pagerAdapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        bind.pager.adapter = pagerAdapter



        return bind.root
    }
}