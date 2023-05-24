package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incode.MainActivity
import com.example.incode.adapters.DriversAdapter
import com.example.incode.databinding.FragmentDriversBinding
import com.example.incode.models.Driver

class DriversFragment: Fragment() {
    private var drivers = ArrayList<Driver>()
    private lateinit var adp: DriversAdapter

    private lateinit var bind: FragmentDriversBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentDriversBinding.inflate(inflater)

        drivers = (activity as MainActivity).drivers
        adp = DriversAdapter()
        adp.driversList.submitList(drivers)

        val driver = drivers[0]

        bind.jobsRecView.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        //binding the layout with data
        bind.activityName.text = driver.name
        bind.activityPart.text = driver.car!!.numberPlate

        return bind.root
    }
}