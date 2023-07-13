package com.example.incode.fragments

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.incode.MainActivity
import com.example.incode.R
import com.example.incode.databinding.FragmentDrawerBinding
import java.io.IOException
import java.util.Locale

class DrawerFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //getting the activity window
        val window = requireActivity().window

        //changing the status bar color
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.drive2)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentDrawerBinding.inflate(inflater)
        setAnimations()

        //getting the users location from the main activity
        //val latitude = (activity as MainActivity).locationLatitude
        //val longitude = (activity as MainActivity).locationLongitude

        //initialising a geo coder object to get the users current location using his longitude and latitude
        val coder = Geocoder(requireContext(), Locale.getDefault())

        //initialising an arraylist to store all the locations gotten
        var userCurrentLocationList = mutableListOf<Address>()

        //getting the locations
//        try{
//            userCurrentLocationList = coder.getFromLocation(latitude!!, longitude!!, 1) as MutableList<Address>
//            Log.d("MainActivity", "onCreate: ${userCurrentLocationList[0].toString()}")
//
//        }catch (e: IOException){
//            Log.d("MainActivity", "error: ${e.message}")
//        }

        //binding the users location
        //bind.welcome.text = "Drive through ${userCurrentLocationList[0].featureName}"

        //getting the navHost fragment
        val navHost = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment

        //binding the navigation drawer with the nav controller
        bind.navView.bringToFront()
        bind.navView.setupWithNavController(navHost.navController)

        //setting the click listeners
        bind.image.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(p0: View?) {
        if (p0 == bind.image){
            bind.drawerNav.openDrawer(GravityCompat.START)
        }
    }

    private fun setAnimations(){
        bind.welcome.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
        )
        bind.slogan.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
        )
        bind.fragmentContainerView2.startAnimation(
            AnimationUtils.loadAnimation(requireContext(), R.anim.zoom_in)
        )
    }
}