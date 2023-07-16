package com.example.incode.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.incode.MainActivity
import com.example.incode.R
import com.example.incode.databinding.FragmentBookRideBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class BookRideFragment : Fragment(), OnMapReadyCallback, View.OnClickListener {
    private lateinit var bind: FragmentBookRideBinding
    private lateinit var googleMap: GoogleMap
    private var userLat: Double? = null
    private var userLong: Double? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = FragmentBookRideBinding.inflate(inflater)

        val supportMapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        supportMapFragment.getMapAsync(this::onMapReady)

        userLat = (activity as MainActivity).locationLatitude
        userLong = (activity as MainActivity).locationLongitude

        return bind.root
    }
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        val sydney = LatLng(userLat!!, userLong!!)
        //googleMap.addMarker(MarkerOptions().position(sydney).title("Sidney city"))
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))


    }

    override fun onClick(v: View?) {
    }

}