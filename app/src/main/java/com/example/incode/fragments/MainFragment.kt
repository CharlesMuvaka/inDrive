package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incode.adapters.RidesAdapter
import com.example.incode.datab.PreviousRides
import com.example.incode.datab.UpComingRides
import com.example.incode.databinding.FragmentMainBinding

class MainFragment : Fragment(){

    private lateinit var bind: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = FragmentMainBinding.inflate(inflater)

        val upComingRides = UpComingRides().upComingRides
        val adp = RidesAdapter(requireContext())
        adp.list.submitList(upComingRides)

        bind.upComing.apply {
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        val previousRides = PreviousRides().previousRides
        val adp1 = RidesAdapter(requireContext())
        adp1.list.submitList(previousRides)
        bind.previousRec.apply {
            adapter = adp1
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        return bind.root
    }

}