package com.example.incode.fragments

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.incode.R
import com.example.incode.databinding.FragmentHomeBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


@Suppress("DEPRECATION")
class HomeFragment: Fragment(), OnMapReadyCallback {
    private lateinit var locationProvider: FusedLocationProviderClient
    private lateinit var bind: FragmentHomeBinding
    private lateinit var googleMap: GoogleMap
    private val locationPermissions: Int = 44

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bind = FragmentHomeBinding.inflate(inflater)

        locationProvider = LocationServices.getFusedLocationProviderClient(requireContext())

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this::onMapReady)

        //getting the users location
        getUsersLocation()

        return bind.root
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == locationPermissions){
            if (grantResults.isNotEmpty() || grantResults[0] == PERMISSION_GRANTED){
                getUsersLocation()
            }
        }
    }

    private fun getUsersLocation() {
        //check if location enabled
        if (isLocationEnabled()){
            //checking if permissions are enabled
            if (ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
            ) {
                locationProvider.lastLocation.addOnCompleteListener {
                    val location = it.result
                    if (location == null){
                        requestNewLocation()
                    }else{
                        val latitude = location.latitude
                        val longitude = location.longitude

                        Toast.makeText(requireContext(), "latitude: $latitude, longitude: $longitude", Toast.LENGTH_SHORT).show()
                    }

                }
            }else{
                //requesting for the permissions
                getPermissions()
            }

        }else{
            //requesting the user to turn on his location settings
            Toast.makeText(requireContext(), "Turn on your location", Toast.LENGTH_SHORT).show()
            val locationIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            requireActivity().startActivity(locationIntent)
        }

    }
    private val locationCallback = object: LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            val lastLocation = p0.lastLocation
            val latitude = lastLocation!!.latitude
            val longitude = lastLocation.longitude
            Toast.makeText(requireContext(), "latitude: $latitude, longitude: $longitude", Toast.LENGTH_SHORT).show()

        }
    }
    //requesting for permissions
    private fun getPermissions(){
        val permissions = arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION)
        Toast.makeText(requireContext(), "Please turn on ", Toast.LENGTH_SHORT).show()
        ActivityCompat.requestPermissions(requireActivity(), permissions, locationPermissions)
    }

    //requesting for the users new location
    private fun requestNewLocation() {
        val request = LocationRequest()
        request.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        request.interval = 5
        request.fastestInterval = 0
        request.numUpdates = 1

        locationProvider = LocationServices.getFusedLocationProviderClient(requireContext())
        if (ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED) {
            locationProvider.requestLocationUpdates(request, locationCallback, Looper.myLooper())

        }else{
            getPermissions()
        }
    }

    //checking if location is enabled
    private fun isLocationEnabled(): Boolean{
        val location = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return location.isProviderEnabled(LocationManager.GPS_PROVIDER) || location.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0
        val sydney = LatLng(-34.0, 151.0)

        googleMap.addMarker(MarkerOptions().position(sydney).title("Sidney city"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onResume() {
        super.onResume()
        //checking if permissions are enabled
        if (ActivityCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) == PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) == PERMISSION_GRANTED
        ){
            getUsersLocation()
        }else{
            getPermissions()
        }

    }
}