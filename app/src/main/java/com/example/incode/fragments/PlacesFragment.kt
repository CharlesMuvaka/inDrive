package com.example.incode.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.incode.MainActivity
import com.example.incode.adapters.PlaceAdapter
import com.example.incode.api.RetrofitClient
import com.example.incode.databinding.FragmentPlacesBinding
import com.example.incode.models.TestResultsOne
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlacesFragment: Fragment(), View.OnClickListener {
    private lateinit var bind: FragmentPlacesBinding

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentPlacesBinding.inflate(inflater)

        val adp = PlaceAdapter(requireContext())
        adp.list.submitList((activity as MainActivity).listGolf)


        bind.rec.apply{
            adapter = adp
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        //setting click listeners
        bind.gym.setOnClickListener(this::onClick)
        bind.movie.setOnClickListener(this::onClick)

        return bind.root
    }

    override fun onClick(p0: View?) {
        if(p0 == bind.gym){
            val adp = PlaceAdapter(requireContext())
            adp.list.submitList((activity as MainActivity).listGyms)

            bind.rec.apply {
                adapter = adp
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
        if(p0 == bind.movie){
            val adp = PlaceAdapter(requireContext())
            adp.list.submitList((activity as MainActivity).listMovie)

            bind.rec.apply {
                adapter = adp
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }
    }
}