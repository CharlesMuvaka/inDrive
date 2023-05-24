package com.example.incode.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.incode.R
import com.example.incode.databinding.FragmentDrawerBinding

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
}